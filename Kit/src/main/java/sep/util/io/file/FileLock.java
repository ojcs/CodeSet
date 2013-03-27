package sep.util.io.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 用于控制文件读取写入并发的锁
 */
public final class FileLock {
	private final File file;
	private int lockerSleepUnitTime = 20;

	private static final Map<File, ReentrantReadWriteLock> lockItemMap = new ConcurrentHashMap<File, ReentrantReadWriteLock>();
	
	public FileLock(final Path path) throws IOException  {
		this(path.toFile());
	}

	public FileLock(final File file) throws IOException {
		if (file == null) {
			throw new NullPointerException();
		}
		this.file = file.getAbsoluteFile();
		lockItemMap.put(this.file, new ReentrantReadWriteLock());
	}

	public synchronized void setWrite(final Status status) {
		switch (status) {
		case Release:
			releaseRead();
			break;
		case Lock:
			lockRead();
			break;
		}
	}
	
	public synchronized void setRead(final Status status) {
		switch (status) {
		case Release:
			releaseWrite();
			break;
		case Lock:
			lockWrite();
			break;
		}
	}
	
	/**
	 * 获取写入锁。将同时获取 写入和读取锁。
	 */
	public synchronized void lockWrite() {
		try {
			final ReentrantReadWriteLock rwLock = lockItemMap.get(file);
			final Lock lock = rwLock.writeLock();
			while (!lock.tryLock() || !rwLock.isWriteLockedByCurrentThread()) {
				Thread.sleep(lockerSleepUnitTime);
			}
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * 等待写入锁释放 此读取锁联动与写入锁，并不能在单纯调用其的情况下锁定文件读取。
	 */
	public synchronized void lockRead() {
		try {
			final Lock lock = lockItemMap.get(file).readLock();
			while (!lock.tryLock()) {
				Thread.sleep(lockerSleepUnitTime);
			}
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * 清理读锁
	 */
	public synchronized void releaseRead() {
		lockItemMap.get(file).readLock().unlock();
	}

	/**
	 * 清理写锁
	 */
	public synchronized void releaseWrite() {
		lockItemMap.get(file).writeLock().unlock();
	}

	public synchronized int getLockerSleepUnitTime() {
		return lockerSleepUnitTime;
	}
	
	public synchronized void setLockerSleepUnitTime(final int lockerSleepUnitTime) {
		this.lockerSleepUnitTime = lockerSleepUnitTime;
	}

	public static enum Status {
		/**
		 * 清理锁
		 */
		Release,
		/**
		 * 上锁
		 */
		Lock
	}
}

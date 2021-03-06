// ==UserScript==
// @name        CSDN免积分下载
// @description 免积分下载
// @version     0.1.5f
//
// @grant       none
//
// @downloadURL	https://github.com/Septembers/CodeSet/raw/master/UserScripts/CSDNFreeDownload.user.js
//
// @include     http://download.csdn.net/detail/*
// @include     http://download.csdn.net/download/*
// ==/UserScript==
jQuery('.res_info .info').after('<a href="javascript:void(0);"><h1>获取免积分下载地址</h1></a>').next().click(function() {
	if (this.href.length === 19) {
		var $this = jQuery(this);
		jQuery.ajax({
			url : '//download.csdn.net/index.php/rest/source/getsourceinfo/' + location.pathname.match(/\d+$/)[0],
			
			dataType : 'json',
			success : function(data) {
				$this
				.attr('href', data.url)
				.attr('title', '文件名:' + data.originfile + '\nMD5:' + data.md5)
				.html('<h1>免积分下载</h1>');
			},
			error : function() {
				$this
				.removeAttr('href')
				.html('<h1>免积分下载地址获取失败。</h1>');
			}
		});
	}
});
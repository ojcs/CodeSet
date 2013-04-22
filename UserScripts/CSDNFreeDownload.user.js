// ==UserScript==
// @name        CSDN免积分下载
// @description CSDN免积分下载
// @version     0.0.2
//
// @grant       none
// @author      @Sep
// @license		MIT License
// 
// @include     http://download.csdn.net/detail/*
// @include     http://download.csdn.net/download/*
// ==/UserScript==
(function($){
	var id = location.pathname.match(/\d+$/)[0];
	
	$('.res_info .info').after('<a href="javascript:void(0)"><h1>免积分下载</h1></a>').next().click(function(){
		if (this.href === 'javascript:void(0)') {
			var $this = $(this);
			$.ajax({
				type : 'get', async : false, dataType : 'JSON',
				url  : 'http://download.csdn.net/index.php/rest/source/getsourceinfo/' + id,
				
				success : function(data) {
					$this.attr('href', JSON.parse(data).url);
				},
				error   : function(error) {
					alert('免积分下载，下载地址获取失败。');
					$this.remove();
				}
			});
		}
	});
})(jQuery);
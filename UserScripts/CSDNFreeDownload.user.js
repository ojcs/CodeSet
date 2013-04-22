// ==UserScript==
// @name        CSDN免积分下载
// @description 
// @version     0.1.3
//
// @grant       none
// @author      @Sep
// @license		MIT License
//
// @include     http://download.csdn.net/detail/*
// @include     http://download.csdn.net/download/*
// ==/UserScript==
(function($){
	$('.res_info .info').after('<a href="javascript:void(0);"><h1>获取免积分下载地址</h1></a>').next().click(function() {
		if (this.href.length === 19) {
			var $this = $(this);
			var url = '//download.csdn.net/index.php/rest/source/getsourceinfo/' + location.pathname.match(/\d+$/)[0];
			$.getJSON(url, function(data) {
				$this.attr('href', data.url).html('<h1>免积分下载</h1>');
			}).fail(function() {
				$this.removeAttr('href').html('<h1>免积分下载地址获取失败。</h1>');
			});
		}
	});
})(jQuery);
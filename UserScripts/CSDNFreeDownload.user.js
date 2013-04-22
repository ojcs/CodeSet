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
(function($, id){
	this.url = 'http://download.csdn.net/index.php/rest/source/getsourceinfo/' + id;
	$('.res_info .info').after('<a href="javascript:void(0)"><h1>免积分下载</h1></a>').next().click(function(){
		if (this.href === 'javascript:void(0)') {
			var $this = $(this);
			$.ajax({
				url  : this.url,
				type : 'get',
				async : false,
				dataType : 'JSON',
				

				success : function(json) {
					$this.attr('href', JSON.parse(json).url);
				},
				error : function() {
					alert('免积分下载地址获取失败。');
					$this.remove();
				}
			});
		}
	});
})(jQuery, location.pathname.match(/\d+$/)[0]);
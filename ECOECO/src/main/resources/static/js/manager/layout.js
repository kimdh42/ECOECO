function PopupCenter(url, title, w, h) {
	// Fixes dual-screen position                         Most browsers      Firefox
	var dualScreenLeft = window.screenLeft != undefined ? window.screenLeft : window.screenX;
	var dualScreenTop = window.screenTop != undefined ? window.screenTop : window.screenY;

	var width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width;
	var height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height;

	var left = ((width / 2) - (w / 2)) + dualScreenLeft;
	var top = ((height / 2) - (h / 2)) + dualScreenTop;
	var newWindow = window.open(url, title, 'scrollbars=no, location=no, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);

	// Puts focus on the newWindow
	if (window.focus) {
		newWindow.focus();
	}
}

function viewtab(idx) {
	var length = $(".list_tab li").length+1 ;
	console.log(length);

	console.log(idx);
	$('.list_tab li').eq(idx-1).addClass('on').siblings().removeClass('on');
	for ( var i = 0; i < length; i ++ ) {
		if ( idx == i ) {
			$(".tabbox_wrap .tab_box").eq(i-1).show();

		}
		else {
			$(".tabbox_wrap .tab_box").eq(i-1).hide();

		}
	}
}

$(document).ready(function(){
	$("li.btn_sub > a").on("click", function() {
		var self = this;
		$(this).parent().siblings().removeClass("on");
		var func = $(this).parent().hasClass("on") ? "slideUp" : "slideDown";

		$("li.btn_sub").not($(self).parent()).find(".sub_menu").slideUp("fast");
		$(this).parent().find(".sub_menu")[func]("fast", function() {
			$("li.btn_sub").not($(self).parent().toggleClass("on")).removeClass("on");
		});
		//$("li.btn_sub > a").removeClass("skin_bg");
		//$("li.btn_sub.on > a").addClass("skin_bg");
		/*
		$(this).toggleClass("skin_bg");
		var submenu = $(this).next(".sub_menu");
		if (submenu.is(":visible")) {
			submenu.slideUp();
		} else {
			submenu.slideDown();
		}
		*/
	});
});

'use strict';

// 大カテゴリ情報処理
$("#bigCategoryList").change(function() {
	$("#middleCategoryList option:nth-child(n+2)").remove();
	let token = $("meta[name='_csrf']").attr("content");
	let header = $("meta[name='_csrf_header']").attr("content");

	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});

	//入力値をセット----①
	let param = {
		path: $("#bigCategoryList option:selected").val(),
		depth: 2,
	}
	//大カテゴリコードを送信URL
	let send_url = "/addItem/findChildCategory";　//----コントローラーのパス
	$.ajax({
		url: send_url,
		type: "POST",
		contentType: "application/json",
		cache: false,
		data: JSON.stringify(param),
		dataType: "json",
		success: function(res) {   //----③
			for (let i = 0; i < res.length; i++) {
				let op = document.createElement("option");
				op.value = res[i].name;
				op.text = res[i].name;
				document.getElementById("middleCategoryList").append(op);
			}
		}
	});
});

// 中カテゴリ情報処理
$("#middleCategoryList").change(function() {
	$("#category option:nth-child(n+2)").remove();
	//入力値をセット----①
	let param = {
		path: $("#bigCategoryList option:selected").val() + "/" + $("#middleCategoryList option:selected").val(),
		depth: 3,
	}
	//データの送信先URL
	let send_url = "/addItem/findChildCategory";　//----②
	$.ajax({
		url: send_url,
		type: "POST",
		contentType: "application/json",
		cache: false,
		data: JSON.stringify(param),
		dataType: "json",
		success: function(res) {   //----③
			for (let i = 0; i < res.length; i++) {
				let op = document.createElement("option");
				op.value = res[i].categoryId;
				op.text = res[i].name;
				document.getElementById("category").append(op);
			}
		}
	});
});
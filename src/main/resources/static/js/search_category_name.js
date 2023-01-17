'use strict';
console.log("検索のjsまできたよ");

// 大カテゴリ情報処理
$("#bigCategory").change(function() {
	console.log("bigCategoryが押された");
	let token = $("meta[name='_csrf']").attr("content");
	let header = $("meta[name='_csrf_header']").attr("content");

	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
	//入力値をセット----①
	let param = {
		id: $("#bigCategory option:selected").val(),
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
			let middleCategoryList = [];
			middleCategoryList.push("<option value=" + "" + ">"
				+ "-- childCategory --" + "</option>");
			for (let i = 0; i < res.length; i++) {
				let middleCategory = "<option value=" + res[i].id + ">"
					+ res[i].name + "</option>";
				middleCategoryList.push(middleCategory);
			}
			$("#middleCategory").html(middleCategoryList);
		}
	});
});

// 中カテゴリ情報処理
$("#middleCategory").change(function() {
	//入力値をセット----①
	let param = {
		id: $("#middleCategory").val(),
	}
	//県コードを送信URL
	let send_url = "/addItem/findChildCategory";　//----②
	$.ajax({
		url: send_url,
		type: "POST",
		contentType: "application/json",
		cache: false,
		data: JSON.stringify(param),
		dataType: "json",
		success: function(res) {   //----③
			let smallCategoryList = [];
			smallCategoryList.push("<option value=" + "" + ">"
				+ "-- grandChild --" + "</option>");
			for (let i = 0; i < res.length; i++) {
				let smallCategory = "<option value=" + res[i].id + ">"
					+ res[i].name + "</option>";
				smallCategoryList.push(smallCategory);
			}
			$("#smallCategory").html(smallCategoryList);
		}
	});
});
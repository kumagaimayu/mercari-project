'use strict';
console.log("jsまできたよ");

// 大カテゴリ情報処理
$("#bigCategoryList").change(function() {
	console.log("bigCategoryListが押された");
	let token = $("meta[name='_csrf']").attr("content");
	let header = $("meta[name='_csrf_header']").attr("content");

	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
	//入力値をセット----①
	let param = {
		id: $("#bigCategoryList option:selected").val(),
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
			$("#middleCategoryList").html(middleCategoryList);
		}
	});
});

// 中カテゴリ情報処理
$("#middleCategoryList").change(function() {
	console.log("middleCategoryListが押された");
	console.log($("#middleCategoryList option:selected").val());
	//入力値をセット----①
	let param = {
		id: $("#middleCategoryList option:selected").val(),
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
			console.log(res);
			let smallCategoryList = [];
			smallCategoryList.push("<option value=" + "" + ">"
				+ "-- grandChild --" + "</option>");
			for (let i = 0; i < res.length; i++) {
				let smallCategory = "<option value=" + res[i].id + ">"
					+ res[i].name + "</option>";
				smallCategoryList.push(smallCategory);
			}
			$("#category").html(smallCategoryList);
		}
	});
});
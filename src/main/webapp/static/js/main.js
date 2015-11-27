/**
 * check the login form before user login.
 * @returns {boolean}
 */
function checkLoginForm(){
	var username = $('#username').val();
	var password = $('#password').val();
	if(isNull(username) && isNull(password)){
		$('#submit').attr('value','请输入账号和密码!!!').css('background','red');
		return false;
	}
	if(isNull(username)){
		$('#submit').attr('value','请输入账号!!!').css('background','red');
		return false;
	}
	if(isNull(password)){
		$('#submit').attr('value','请输入密码!!!').css('background','red');
		return false;
	}
	//if(username != 'Amaya' || password != '123456'){
	//	$('#submit').attr('value','账号或密码错误!!!').css('background','red');
	//	return false;
	//}
	else{
		$('#submit').attr('value','Logining~');
		return true;
	}
}

/**
 * check the param if it's null or '' or undefined
 * @param input
 * @returns {boolean}
 */
function isNull(input){
	if(input == null || input == '' || input == undefined){
		return true;
	}
	else{
		return false;
	}
}


function checkContactAddForm(){
	var name = $("#add-name").val();
	var email = $("#add-email").val();
	var groups = $("#add-groups").val();
	var reg_email = /^[A-Za-z0-9][\w\-\.]{3,12}@([\w\-]+\.)+[\w]{2,3}$/;
	var reg_number = /^[1-9][0-9]*$/;
	if(name == null || name == ""){
		$.scojs_message("姓名不能为空,请重新输入!", $.scojs_message.TYPE_ERROR);
		return false;
	}
	if(name.length<2){
		$.scojs_message("姓名过短,请重新输入!", $.scojs_message.TYPE_ERROR);
		return false;
	}
	if(!reg_email.test(email)){
		$.scojs_message("邮箱格式错误,请重新输入!", $.scojs_message.TYPE_ERROR);
		return false;
	}
	if(!reg_number.test(groups)){
		$.scojs_message("分组格式有误,请重新输入!", $.scojs_message.TYPE_ERROR);
		return false;
	}
	return true;
}

function checkContactEditForm(){
	var name = $("#edit-name").val();
	var email = $("#edit-email").val();
	var groups = $("#edit-groups").val();
	var reg_email = /^[A-Za-z0-9][\w\-\.]{3,12}@([\w\-]+\.)+[\w]{2,3}$/;
	var reg_number = /^[0-9]+.[0-9]*$/;
	if(name == null || name == ""){
		$.scojs_message("姓名不能为空,请重新输入!", $.scojs_message.TYPE_ERROR);
		return false;
	}
	if(name.length<2){
		$.scojs_message("姓名过短,请重新输入!", $.scojs_message.TYPE_ERROR);
		return false;
	}
	if(!reg_email.test(email)){
		$.scojs_message("邮箱格式错误,请重新输入!", $.scojs_message.TYPE_ERROR);
		return false;
	}
	if(!reg_number.test(groups)){
		$.scojs_message("分组格式有误,请重新输入!", $.scojs_message.TYPE_ERROR);
		return false;
	}
	return true;
}

function checkGroupsAddForm(){
	var name = $("#add-name").val();
	if(name == null || name == ""){
		$.scojs_message("分组名不能为空,请重新输入!", $.scojs_message.TYPE_ERROR);
		return false;
	}
	if(name.length<2){
		$.scojs_message("分组名过短,请重新输入!", $.scojs_message.TYPE_ERROR);
		return false;
	}
	return true;
}

function checkGroupsEditForm(){
	var name = $("#edit-name").val();
	if(name == null || name == ""){
		$.scojs_message("分组名不能为空,请重新输入!", $.scojs_message.TYPE_ERROR);
		return false;
	}
	if(name.length<2){
		$.scojs_message("分组名过短,请重新输入!", $.scojs_message.TYPE_ERROR);
		return false;
	}
	return true;
}

function checkSettingEditForm(){
	var sendmail = $("#sendmail").val();
	var sendname = $("#sendname").val();
	var sendpass = $("#sendpass").val();
	var smtpserver = $("#smtpserver").val();
	var smtpport = $("#smtpport").val();
	var reg_email = /^[A-Za-z0-9][\w\-\.]{3,12}@([\w\-]+\.)+[\w]{2,3}$/;
	var reg_number = /^[0-9]+.[0-9]*$/;
	if(!reg_email.test(sendmail)){
		$.scojs_message("邮箱账号不符合规则!", $.scojs_message.TYPE_ERROR);
		return false;
	}
	if(sendname == null || sendname == ""){
		$.scojs_message("发信昵称不能为空!", $.scojs_message.TYPE_ERROR);
		return false;
	}
	if(sendname.length < 2){
		$.scojs_message("发信昵称过短!", $.scojs_message.TYPE_ERROR);
		return false;
	}
	if(sendpass == null || sendpass == ""){
		$.scojs_message("发信密码不能为空!", $.scojs_message.TYPE_ERROR);
		return false;
	}
	if(smtpserver == null || smtpserver == ""){
		$.scojs_message("smtp服务器不能为空!", $.scojs_message.TYPE_ERROR);
		return false;
	}
	if(!reg_number.test(smtpport)){
		$.scojs_message("smtp端口不符合规则!", $.scojs_message.TYPE_ERROR);
		return false;
	}
	return true;
}

function showSetting(){
	$.ajax({
		url : 'setting/show',
		datatype : 'json',
		type : 'post',
		data : {},
		beforeSend : function(){

		},
		success : function(data){
			if(data != null){
				var obj = eval(data);
				$("#sendmail").val(obj.sendmail);
				$("#sendname").val(obj.sendname);
				$("#sendpass").val(obj.sendpass);
				$("#smtpserver").val(obj.smtpserver);
				$("#smtpport").val(obj.smtpport);
			}else{
				$.scojs_message("未知错误,无法加载设置!", $.scojs_message.TYPE_ERROR);
			}
		}
	});
}

function showContactor(){
	$.ajax({
		url : 'contact/all',
		datatype : 'json',
		type : 'post',
		data : {
			page : 1
		},
		beforeSend : function(){

		},
		success : function(data){
			if(data != null){
				var obj = eval(data);
				console.log(obj);
			}
		}
	});
}

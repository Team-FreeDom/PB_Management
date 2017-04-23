// JavaScript Document
$(document).ready(function() {
    ////数据格式{planstime:"2016-12-1",planetime:"2016-12-30",rentstime:"2016-12-30",rentetime:"2017-12-30"}//////////
    $.ajax({ //页面加载时自动执行
        type: 'POST',
        dataType: 'json',
        url: 'getLandApplyDate.do', ///修改此处
        async: false,
        cache: false,
        error: function(request) {
            bootbox.alert({
                message: "加载数据失败",
                size: 'small'
            });
            return false;
        },
        success: function(data) {
            var i = 0;
            var planstime = data.planstime;
            var planetime = data.planetime;
            var rentstime = data.rentstime;
            var rentetime = data.rentetime;
            var limitday=data.limitday;
            if (planstime == '') {
                $("#start").show();
                $("#set").hide();
            } else {
                $("#start").hide();
                $("#set").show();
                $('#demo').val(planstime);
                $('#demo2').val(planetime);
                $('#demo3').val(rentstime);
                $('#demo4').val(rentetime);
                $('#day').val(limitday);
            }

        }
    });

    $("#start-btn").click(function() {
        $('#demo').val('');
        $('#demo2').val('');
        $('#demo3').val('');
        $('#demo4').val('');
        $('#day').val('');
        $("#start").hide();
        $("#set").show();
    });

    function toDate(str) {
        var sd = str.split("-");
        return new Date(sd[0], sd[1], sd[2]);
    }


    $(document).on("click", "#save", function() {
    	
    	//正则表达式
    	var reg=/^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/;
    	
        var planstime = $("#demo").val();
        var planetime = $("#demo2").val();
        var rentstime = $("#demo3").val();
        var rentetime = $("#demo4").val();
        var limitday=$('#day').val();
        if (!reg.test(planstime)) {
            bootbox.alert({
                message: "请设置正确申请开始时间!",
                size: 'small'
            });
            return;
        } else if (!reg.test(planetime)) {
            bootbox.alert({
                message: "请设置正确申请结束时间!",
                size: 'small'
            });
            return;
        } else if (!reg.test(rentstime)) {
            bootbox.alert({
                message: "请设置正确租赁开始时间!",
                size: 'small'
            });
            return;
        } else if (!reg.test(rentetime)) {
            bootbox.alert({
                message: "请设置正确租赁结束时间!",
                size: 'small'
            });
            return;
        }else if (limitday == "") {
            bootbox.alert({
                message: "请填写交费限制天数!",
                size: 'small'
            });
        } else {
            var planstime_data = toDate(planstime);
            var planetime_data = toDate(planetime);
            var rentstime_data = toDate(rentstime);
            var rentetime_data = toDate(rentetime);
            if ((planstime_data > planetime_data) || (rentstime_data > rentetime_data)) {
                bootbox.alert({
                    message: "日期时间段选择错误",
                    size: 'small'
                });
                return false;
            }
            //var str = '';
            //str = '{planstime:"' + planstime + '",planetime:"' + planetime + '",rentstime:"' + rentstime + '",rentetime:"' + rentetime + '"}';
            ////数据格式{planstime:"2016-12-1",planetime:"2016-12-30",rentstime:"2016-12-30",rentetime:"2017-12-30"}//////////

            $.ajax({ //以文本方式提交申请
                type: 'POST',
                dataType: 'json',
                data: {
                	planstime:planstime,
                	planetime:planetime,
                	rentstime:rentstime,
                	rentetime:rentetime,
                	limitday:limitday
                },
                url: 'updateLandApplyDate.do', //修改此处
                async: false,
                cache: false,
                error: function(request) {
                    obj.dialog = bootbox.alert({
                        message: "网络故障，请稍后重试",
                        size: 'small'
                    });
                    return false;
                }, ///error
                success: function(data) {
                	if(data.flag){
                        bootbox.alert({
                             message: "设置成功！",
                             size: 'small'
                         });
                 	}
                    } //success
            }); //ajax


        }
    });

    $("#end-btn").click(function() {

    	bootbox.confirm({
			message: "确定结束本次租赁工作吗？",
			size: 'small',
			buttons: {
				confirm: {
					label: '确定',
					className: 'btn-success'
				},
				cancel: {
					label: '取消',
					className: 'btn-danger'
				},
			},			
			callback: function (result) {
				if (result) {
					$.ajax({ //以文本方式提交申请,command=1表示结束土地租赁工作，清空土地租赁申请表，清空消息表
                        type: 'POST',
                        dataType: 'text',
                        data: {
                            "command": "1"
                        },
                        url: 'endLandApply.do', //修改此处
                        async: false,
                        cache: false,
                        error: function(request) {
                            alert('网络故障，请稍后重试');
                        }, ///error
                        success: function(data) {
                        	 bootbox.alert({
                                 message: "土地租赁工作结束！",
                                 size: 'small'
                             });
                                $("#start").show();
                                $("#set").hide();
                            } //success
                    });
                }
			}
	});

    });
});

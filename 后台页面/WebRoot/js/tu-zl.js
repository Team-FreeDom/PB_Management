// JavaScript Document

var aCollege = new Array("生物科学技术学院", "资源环境学院", "动物科学技术学院", "农学院", "动物医学院", "园艺园林学院", "植物保护学院", "其他");
var aClass = new Array("sk_college", "zh_college", "dw_college", "nx_college", "dy_college", "yx_college", "zb_college", "other_college");
$(function() {
    var options = {
        width: 20,
        float: true,
        disableDrag: true,
        disableResize: true
    };
    $('.grid-stack').gridstack(options);
    /*获得基地------start*/
    $.ajax({ //页面加载时自动执行
        type: 'POST',
        dataType: 'json',
        url: 'getBase_deptInfo.do',
        async: false,
        cache: false,
        error: function(request) {
            bootbox.alert({
                message: "加载区域失败",
                size: 'small'
            });
        },
        success: function(data) {
            var i = 0;
            var array1 = data.base;
            var array2 = data.xy;
            var stime = data.stime;
            var etime = data.etime;
            var tag=data.tag;
           // if (stime == '' || etime == '') {
            if (tag==0) {
                bootbox.alert({
                    message: "土地租赁尚未开放，请等候通知",
                    size: 'small'
                });
                return false;
            }
            if(tag==1){
            	bootbox.alert({
                    message: "目前不属于租赁时间段",
                    size: 'small'
                });
                return false;
            }           
            
            $('#stime').val(stime);
            $('#etime').val(etime);
            for (var item in array1) {
                $("#choose-gridh").after(
                    "<option value=" + array1[i].bid + ">" +
                    array1[i].bname + "</option>");
                i++;
            }
            i = 0;
            for (var item in array2) {
                $("#scollageh").after(
                    "<option value=" + array2[i].aid + ">" +
                    array2[i].dept + "</option>");
                i++;
            }
        }
    });

    var tu_zl = new function() {
            this.choose_count = 0;
            this.serializedData = []; //在loadGrid中构成布局对象，关联土地编号id
            this.dialog;
            this.grid = $('.grid-stack').data('gridstack');

            function fill(id, name, aptCollege, plantingContent, landArea, buildingArea, tudi_Afford, img) {
                $('#tudi_id').val(id);
                $('#tudi_name').val(name);
                $("#tudi_college").val(aCollege[aptCollege]);
                $('#tudi_plantingContent').val(plantingContent);
                $('#tudi_landArea').val(landArea);
                $('#tudi_buildingArea').val(buildingArea);
                $('#tudi_Afford').val(tudi_Afford);
                $('#imghead').prop("src", img);
            };

            function closeboxEX() {
                $('#land_lease_table').html('');
                tu_zl.loadGrid();
                tu_zl.dialog.modal('hide');
                $('#landModal').modal('hide');
            }

            function closebox() {
                tu_zl.dialog.modal('hide');
            }
            function subString(str, len, hasDot)
            {
                var newLength = 0;
                var newStr = "";
                var chineseRegex = /[^\x00-\xff]/g;
                var singleChar = "";
                var strLength = str.replace(chineseRegex,"**").length;
                for(var i = 0;i < strLength;i++)
                {
                    singleChar = str.charAt(i).toString();
                    if(singleChar.match(chineseRegex) != null)
                    {
                        newLength += 2;
                    }
                    else
                    {
                        newLength++;
                    }
                    if(newLength > len)
                    {
                        break;
                    }
                    newStr += singleChar;
                }

                if(hasDot && strLength > len)
                {
                    newStr += "...";
                }
                return newStr;
            }

            this.loadGrid = function() {
                var bid = $('#choose-grid').children('option:selected').val();
                var obj = this;
                fill('', '', '', '', '', '', '', '');
                $('#field_rent tbody').html('');
                this.grid.removeAll();
                var obj = this;
                if (bid == "") {
                    return;
                }
                $.ajax({
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        "bid": bid
                    },
                    url: 'getRentCollection.do', ////修改此处服务器文件即可
                    async: false,
                    cache: false,
                    error: function(request) {
                        bootbox.alert({
                            message: "数据加载失败！",
                            size: 'small'
                        });
                    },
                    success: function(data) {
                            obj.serializedData = data;
                            var items = GridStackUI.Utils.sort(obj.serializedData);
                            _.each(items, function(node) {

                                if (node.name != '')
                                    obj.grid.addWidget($('<div><div class="grid-stack-item-content Havetorent"><span class="lname">从事：' + node.planting + '</span><span class="label label-warning  Lineup">' + node.name + '</span></div><div></div></div>'), node.x, node.y, node.width, node.height, false, 1, 4, 1, 4, node.id);
                                else if (node.tag == 1 && node.name == '') {
                                    obj.grid.addWidget($('<div><div class="grid-stack-item-content lockdiv"><span class="lname">锁定：等候缴费</span><span class="label label-warning  Lineup">' + node.name + '</span></div><div></div></div>'), node.x, node.y, node.width, node.height, false, 1, 4, 1, 4, node.id);
                                } else
                                    obj.grid.addWidget($('<div><div class="grid-stack-item-content ' + aClass[node.aptCollege] + '"><label class="checkbox-inline lname"><input type="checkbox" class="ck" id=' + node.id + ' value=' + node.id + '>' + subString(node.lname,4) + '</label><span class="label label-primary Lineup "><span class="glyphicon glyphicon-user pull-right"></span>' + node.lineup + '</span></div><div></div></div>'), node.x, node.y, node.width, node.height, false, 1, 4, 1, 4, node.id);
                            }, obj); //end each
                        } //end success
                }); //end ajax
            }.bind(this);


            this.savedate = function() {
                if (this.choose_count <= 0) {
                    bootbox.alert({
                        message: "您没有选择土地",
                        size: 'small'
                    });
                    return false;
                }
                var str = ' ';
                var plan;
                var landid;
                var userid = $.cookie("username"); ////////////此处需要修改
                if (userid == '' || userid == undefined) {
                    bootbox.alert({
                        message: "登录失效，请正返回首页",
                        size: 'small'
                    });
                    window.location.href = "../login_soft.html";
                }

                var stime = $('#stime').val();
                var etime = $('#etime').val();
                var lidList = '(';
                var bname = $('#choose-grid').children('option:selected').html(); //选中的基地编号
                var info_str = '[';


                for (var i = 1; i <= this.choose_count; i++) {
                    plan = $('#plan' + i).val();
                    xy = $('#scollage').val();
                    if (plan == '' || xy == '') {
                        bootbox.alert({
                            message: "从事工作和申报学院为必填项目",
                            size: 'small'
                        });
                        $('#plan' + i)[0].focus();
                        return false;
                    }
                    landid = $('#tuname' + i).attr('tid');
                    lidList = lidList + "'" + landid + "'";
                    if (i == 1) {
                        str = str + "('" + landid + "','" + stime + "','" + etime + "','" + plan + "','" + userid + "'," + xy;
                        info_str = info_str + '{userid:"' + userid + '",msg:"' + bname + '#' + landid + '"}';
                    } else {
                        str = str + "('" + landid + "','" + stime + "','" + etime + "','" + plan + "','" + userid + "'," + xy;
                        info_str = info_str + ',{userid:"' + userid + '",msg:"' + bname + '#' + landid + '"}';
                    }


                    if (i == this.choose_count) {
                        str = str + ",2)";
                        lidList = lidList + ")";
                    } else {
                        str = str + ",2),";
                        lidList = lidList + ",";
                    }
                }
                info_str = info_str + ']';
                userid = "'" + userid + "'";
                //end for
                // alert(str);////INSERT INTO tab_comp VALUES(item1, price1, qty1),(item2, price2, qty2),(item3, price3, qty3);批量插入语句
                var obj = this;
                $.ajax({ //以文本方式提交申请
                    type: 'POST',
                    dataType: 'text',
                    data: {
                        "str": str,
                        "info_str": info_str,
                        "lidList": lidList,
                        "userid": userid
                    },
                    url: 'submitLandApply.do', //修改
                    async: false,
                    cache: false,
                    error: function(request) {
                        obj.dialog = bootbox.alert({
                            message: "网络故障，请稍后重试",
                            size: 'small'
                        });
                    }, ///error
                    success: function(data) {
                            var position = data.indexOf('$');
                            var flag = data.substring(0, position);


                            if (flag != 1) {

                                bootbox.alert({
                                    message: "请先撤回相同的土地申请",
                                    size: 'small',
                                    title: "申请失败",
                                });

                            } else {


                                fill('', '', '', '', '', '', '', '');
                                $('#field_rent tbody').html('');
                                obj.dialog = bootbox.dialog({
                                    message: '<p class="text-center">数据提交成功，正返回中......</p>',
                                    closeButton: false
                                });
                                window.setTimeout(closeboxEX, 1000);
                            }
                        } //success
                }); //ajax

            }.bind(this);
            ////滚轮缩放/////////////////////////////////////////
            $('#belowImg').on('mousewheel', function(event) {
                if (event.deltaY > 0) {
                    if ($('#belowImg').width() <= 1400){
                        $('#belowImg').width($('#gridmouse').width() + 100);
                        $('#belowImg').height($('#gridmouse').width());
                      }
                } else {
                    if ($('#belowImg').width() >= 868){
                        $('#belowImg').width($('#gridmouse').width() - 100);
                        $('#belowImg').height($('#gridmouse').width());
                      }
                }
                return false;
            });
            $(document).on("click", "#girdin", function() {
              if ($('#gridmouse').width() <= 1700)
                  $('#gridmouse').width($('#gridmouse').width() + 100);
            });
            $(document).on("click", "#girdout", function() {
              if ($('#gridmouse').width() >= 868)
                  $('#gridmouse').width($('#gridmouse').width() - 100);
            });

            $(document).on("click", ".grid-stack-item", function() {
              //if(e.target.tagName=="INPUT")
                  //return;
                var id = $(this).attr('data-gs-id');
                //var oBox = document.getElementById(id);
                //oBox.click();
                var cvalue=$("#"+id).prop("checked");
                $("#"+id).prop("checked",!cvalue);

                var n = _.findIndex(tu_zl.serializedData, 'id', id);
                if (tu_zl.serializedData[n].name != '') return false;
                if (!cvalue) {
                    fill(id, tu_zl.serializedData[n].lname, tu_zl.serializedData[n].aptCollege, tu_zl.serializedData[n].plantingContent, tu_zl.serializedData[n].landArea, tu_zl.serializedData[n].buildingArea,
                        tu_zl.serializedData[n].afford, tu_zl.serializedData[n].img);
                    var testArray = tu_zl.serializedData[n].data;
                    var data = new Array();
                    i = 0;
                    _.map(testArray, function(el) {
                        data[i] = new Array();
                        data[i][0] = el.name;
                        data[i][1] = el.planting;
                        data[i][2] = el.ptime;
                        i++;
                    });
                    $('#field_rent').dataTable({
                        "destroy": true,
                        "data": data,
                        "paging": false,
                        "searching": false,
                        "ordering": false,
                        "info": false,
                        "order": [
                            [2, "asc"]
                        ]
                    });
                } else {
                    fill('', '', '', '', '', '', '', '');
                    $('#field_rent tbody').html('');
                }

            }).bind(this);

            $(document).on("click", "#sum_app", function() {
            	$("#modalHeight").removeClass("modalHeight");
                var i = 1;
                var str = '';
                var id = -1;
                var n = -1;
                $('#scollage').val("");
                $.each($('.ck'), function() {
                    if ($(this).is(':checked')) {
                        id = $(this).attr('id');
                        n = _.findIndex(tu_zl.serializedData, 'id', id);
                        str = str + '<div class="form-group ">\
					<div class="input-group">\
					  <label>土地租赁' + i + '：</label>\
					  <input class="form-control" type="text" value=' + tu_zl.serializedData[n].lname + ' id="tuname' + i + '" tid=' + id + ' disabled>\
					  </div>\
					</div>\
					<div class="form-group">\
					  <div class="input-group">\
						<label>推荐从事：</label>\
						<input class="form-control" type="text" value="' + tu_zl.serializedData[n].plantingContent + '"  id="tplan' + i + '" disabled>\
					  </div>\
					</div>\
					<div class="form-group">\
					  <div class="input-group">\
					  <label>申请从事：</label>\
					  <input type="text" class="form-control" id="plan' + i + '" placeholder="必填从事项目">\
					  </div>\
					</div>\
					<p></p> '

                        i++;
                    } //end if
                }); //end each
                if(i>6){
                	$("#modalHeight").addClass("modalHeight");
                }
                if (str == '') {
                    $('#sub_land_apply').attr('disabled', "true");
                    return false;
                }
                $('#land_lease_table').html(str);
                $('#sub_land_apply').removeAttr("disabled");
                tu_zl.choose_count = i - 1;
            });

          /*  $(document).on("click", ".ck", function() {
                var id = $(this).attr('id');
                var n = _.findIndex(tu_zl.serializedData, 'id', id);
                if (tu_zl.serializedData[n].name != '') return false;
                if ($(this).is(':checked')) {
                    fill(id, tu_zl.serializedData[n].lname, tu_zl.serializedData[n].aptCollege, tu_zl.serializedData[n].plantingContent, tu_zl.serializedData[n].landArea, tu_zl.serializedData[n].buildingArea,
                        tu_zl.serializedData[n].afford, tu_zl.serializedData[n].img);
                    var testArray = tu_zl.serializedData[n].data;
                    var data = new Array();
                    i = 0;
                    _.map(testArray, function(el) {
                        data[i] = new Array();
                        data[i][0] = el.name;
                        data[i][1] = el.planting;
                        data[i][2] = el.ptime;
                        i++;
                    });
                    $('#field_rent').dataTable({
                        "destroy": true,
                        "data": data,
                        "paging": false,
                        "searching": false,
                        "ordering": false,
                        "info": false,
                        "order": [
                            [2, "asc"]
                        ]
                    });
                } else {
                    fill('', '', '', '', '', '', '', '');
                    $('#field_rent tbody').html('');
                }
            }); //end click*/

            $('#choose-grid').change(this.loadGrid);
            $('#sub_land_apply').click(this.savedate);
        } //end tu_zl

}); //end function

function displayImg() {

    var img = document.getElementById('belowImg');
    img.src = document.getElementById('imghead').src;

}

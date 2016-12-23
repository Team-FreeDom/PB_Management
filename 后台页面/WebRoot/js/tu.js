// JavaScript Document

var aClass = new Array("sk_college", "zh_college", "dw_college", "nx_college", "dy_college", "yx_college", "zb_college", "other_college");

$(function() {
    var options = {
        width: 20,
        float: true
    };
    $('.grid-stack').gridstack(options);



    /*获得基地------start*/
    $.ajax({ //页面加载时自动执行
        type: 'POST',
        dataType: 'json',
        url: 'baseInfo.do',
        async: false,
        cache: false,
        error: function(request) {
            bootbox.alert({
                message: "加载失败",
                size: 'small'
            });
        },
        success: function(data) {
            var i = 0;
            for (var item in data) {

                $("#load-gridh").after(
                    "<option value=" + data[i].bid + ">" +
                    data[i].bname + "</option>");

                i++;
            }

        }

    });
    /*获得基地------end*/
    $(document).on("click", "#upimg", function() {
        $('#imgfile').click();
    });
    $('#preview').click(function() {
        //$('#myModal').modal('show')
        var img = document.getElementById('belowImg');
        img.src = document.getElementById('imghead').src;
    });
    /*返回的data格式
    {imgurl:'../image/1.png',msg:'success'}
    */
    $('#imgfile').change(function() {
        var t = parseInt($('#load-grid').children('option:selected').val());
        if (!_.isEmpty(tudi.serializedData2)) {
            if (t != tudi.serializedData2[0].bid) {
                bootbox.alert({
                    message: "操作失败",
                    size: 'small'
                });
                return false;
            }
        }
        if (_.isEmpty(tudi.serializedData2)) {
            bootbox.alert({
                message: "请先选择土地",
                size: 'small'
            });
            return false;
        }
        var filepath = $(this).val();
        var file_size = this.files[0].size;
        var size = file_size / 1024;
        var extStart = filepath.lastIndexOf(".");
        var ext = filepath.substring(extStart, filepath.length).toUpperCase();
        if (ext != ".BMP" && ext != ".PNG" && ext != ".GIF" && ext != ".JPG" && ext != ".JPEG") {
            bootbox.alert({
                message: "图片限于bmp,png,gif,jpeg,jpg格式",
                size: 'small'
            });
            return false;
        }
        if (size > 1024 * 3) {
            bootbox.alert({
                message: "上传的图片大小不能超过3M！",
                size: 'small'
            });
            return false;
        }
        $.ajaxFileUpload({
            url: 'uploadImage.do', //用于文件上传的服务器端请求地址
            secureuri: false, //是否需要安全协议，一般设置为false
            fileElementId: 'imgfile', //文件上传域的ID
            dataType: 'json', //返回值类型
            success: function(data) //服务器成功响应处理函数
                {
                    $("#imghead").attr("src", data.imgurl);

                },
            error: function(data) //服务器响应失败处理函数
                {
                    $("#imghead").attr("src", "");
                }
        });
        return false;
    });

    var tudi = new function() {
        this.isModify = false;
        this.serializedData = []; //在loadGrid中构成布局对象，关联土地编号id
        this.serializedData2 = []; //在loadGrid中构成土地信息对象,关联土地编号id
        this.grid = $('.grid-stack').data('gridstack');

        function fill(id, name, aptCollege, plantingContent, landArea, buildingArea, tudi_Afford, img) {
            $('#tudi_id').val(id);
            $('#tudi_name').val(name);
            $("#tudi_aptCollege").val(aptCollege);
            $('#tudi_plantingContent').val(plantingContent);
            $('#tudi_landArea').val(landArea);
            $('#tudi_buildingArea').val(buildingArea);
            $('#tudi_Afford').val(tudi_Afford);
            $('#imghead').prop("src", img);
        };

        function fuyuan() {
            var obj = $(".gay");
            obj.removeClass("gay");
            obj.addClass(obj.attr("id"));
            fill('', '', '', '', '', '', '', ''); //加*
        };

        function ishunluan() {
            var t = parseInt($('#load-grid').children('option:selected').val());
            if (!_.isEmpty(tudi.serializedData2)) {
                if (t != tudi.serializedData2[0].bid) {

                    bootbox.alert({
                        message: "操作失败",
                        size: 'small'
                    });
                    return false;
                }
            }
            return true;
        };
        ////滚轮缩放/////////////////////////////////////////
        $('#gridmouse').on('mousewheel', function(event) {
            /*if (event.deltaY > 0) {
                if ($('#gridmouse').width() <= 1400)
                    $('#gridmouse').width($('#gridmouse').width() + 100);
            } else {
                if ($('#gridmouse').width() >= 868)
                    $('#gridmouse').width($('#gridmouse').width() - 100);
            }
            return false;*/
        });
        $(document).on("click", "#girdin", function() {
          if ($('#gridmouse').width() <= 1400)
              $('#gridmouse').width($('#gridmouse').width() + 100);
        });
        $(document).on("click", "#girdout", function() {
          if ($('#gridmouse').width() >= 868)
              $('#gridmouse').width($('#gridmouse').width() - 100);
        });
        /////////////////单击删除按钮事件/////////////////////////////////
        $(document).on("click", ".grid-stack-item .close", function() {

            var el = $(this).parents('.grid-stack-item');
            var n = _.findIndex(tudi.serializedData, 'id', $(el).attr('data-gs-id'));
            if (n == -1) {

                bootbox.alert({
                    message: "无此节点，操作失败",
                    size: 'small'
                });
                return false;
            }
            if (!ishunluan()) {
                return false;
            }
            _.remove(tudi.serializedData, function(n) {
                return n.id == $(el).attr('data-gs-id');
            });
            _.remove(tudi.serializedData2, function(n) {
                return n.id == $(el).attr('data-gs-id');
            });
            tudi.grid.removeWidget(el);
            fuyuan();
            this.isModify = true;
            return true;
        });

        ////////////////////单击结点选择事件/////////////////////////////
        $(document).on("click", ".grid-stack-item", function() {
            $("#file").val("");
            if (!ishunluan()) {
                return false;
            }
            var id = $(this).attr('data-gs-id');
            var n = _.findIndex(tudi.serializedData2, 'id', id);
            if (n < 0)
                return false;
            //将先前被选中的土地移除 为gay的class
            var objGay = $(".gay");
            objGay.removeClass("gay");
            objGay.addClass(objGay.attr("id"));
            $(this).children('div.grid-stack-item-content').removeClass($(this).children('div.grid-stack-item-content').attr("id"));
            $(this).children('div.grid-stack-item-content').addClass('gay');
            fill(id, tudi.serializedData2[n].lname, tudi.serializedData2[n].college, tudi.serializedData2[n].plantingContent, tudi.serializedData2[n].landArea, tudi.serializedData2[n].buildingArea, tudi.serializedData2[n].Afford, tudi.serializedData2[n].img);
            return true; //加*
        }); ///单击结点事件

        this.loadGrid = function() {
            var bid = $('#load-grid').children('option:selected').val();
            var obj;
            if (this.isModify) {
                if (confirm('您上次的操作尚未保存，是否取消跳转?')) {
                    //this.savedata();
                    $('#load-grid').val(this.serializedData2[0].bid);
                    return false;
                }
            }
            fill('', '', '', '', '', '', '', '');
            this.grid.removeAll();
            obj = this;
            if (bid == "") {
                return;
            }
            $.ajax({
                type: 'POST',
                dataType: 'json',
                data: {
                    "bid": bid
                },
                url: 'getDifferLayout.do',
                async: false,
                cache: false,
                error: function(request) {

                    bootbox.alert({
                        message: "数据加载失败！",
                        size: 'small'
                    });
                },
                success: function(data) {

                        obj.serializedData2 = _.map(data, function(el) {
                            return {
                                id: el.id,
                                lname: el.lname,
                                college: el.aptCollege, //加*
                                plantingContent: el.plantingContent,
                                landArea: el.landArea,
                                buildingArea: el.buildingArea,
                                Afford: el.afford,
                                img: el.img,
                                bid: el.bid
                            };
                        });
                        obj.serializedData = _.map(data, function(el) {
                            return {
                                x: el.x,
                                y: el.y,
                                width: el.width,
                                height: el.height,
                                id: el.id,
                                aptCollege: el.aptCollege,
                            };
                        });
                        var items = GridStackUI.Utils.sort(obj.serializedData);
                        _.each(items, function(node) {
                            var name = _.result(_.find(obj.serializedData2, function(chr) {
                                return chr.id == node.id;
                            }), 'lname'); ////对象serializedData2查找关联的lname
                            //  alert(aClass[node.aptCollege]);
                            obj.grid.addWidget($("<div><div class=\"grid-stack-item-content " + aClass[node.aptCollege] + "\"" + " id=\"" + aClass[node.aptCollege] + "\"><div class=\"lname\">" + name + "</div><button type=\"button\" class=\"close\" ><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span></button></div><div></div></div>"), node.x, node.y, node.width, node.height, false, 1, 4, 1, 4, node.id); //����ڵ�
                        }, obj); //end each

                    } //end success
            }); //end ajax

        }.bind(this); ////loadgrid

        ///////////////////savedate///////////////////////////////////
        this.savedata = function() {
            //////
            var c = "";
            bid = $('#load-grid').children('option:selected').val();
            var tag = 1;


            if (_.isEmpty(this.serializedData)) {
                tag = 0; //1.

                //return true;
            } else {
                this.serializedData = _.map($('.grid-stack > .grid-stack-item:visible'), function(el) {
                    el = $(el);
                    var node = el.data('_gridstack_node');
                    return {
                        x: node.x,
                        y: node.y,
                        width: node.width,
                        height: node.height,
                        id: node.id.toString()
                    };
                }, this);
                var c = [];
                for (var i in this.serializedData) {
                    //this.serializedData[i].id;
                    for (var j in this.serializedData2) {
                        if (this.serializedData[i].id == this.serializedData2[j].id) {
                            var temp = {};
                            temp.id = this.serializedData2[j].id;
                            temp.x = this.serializedData[i].x;
                            temp.y = this.serializedData[i].y;
                            temp.width = this.serializedData[i].width;
                            temp.height = this.serializedData[i].height;

                            temp.Afford = this.serializedData2[j].Afford;
                            temp.buildingArea = this.serializedData2[j].buildingArea;
                            temp.bid = this.serializedData2[j].bid;
                            temp.college = this.serializedData2[j].college;
                            temp.img = this.serializedData2[j].img;
                            temp.landArea = this.serializedData2[j].landArea;
                            temp.lname = this.serializedData2[j].lname;
                            temp.plantingContent = this.serializedData2[j].plantingContent;
                            c.push(temp);
                            break;
                        }
                    }
                }

            }
            ////////////////////////////////将JSON.stringify(c)发送过去////////////////////////////////////////////////
            var json = "" + JSON.stringify(c);
            $.ajax({
                type: 'POST',
                dataType: 'json',
                data: {
                    "bid": bid,
                    "tag": tag,
                    "layinfo": json

                },
                url: 'updateLayout_Info.do',
                async: false,
                cache: false,
                error: function(request) {
                    bootbox.alert({
                        message: "加载失败!",
                        size: 'small'
                    });
                },
                success: function(data1) {
                    bootbox.alert({
                        message: "已同步至服务器，保存成功!",
                        size: 'small'
                    });
                }
            });

            /////////////////////////////////////////////////////////////////////////////////////////////////////////////
            fuyuan();
            this.isModify = false;
            return true;
        }.bind(this);


        ///////////////////更新到服务器///////////////////////////////////////////////
        this.saveGrid = function() {
            if (!ishunluan()) {
                return false;
            }
            this.savedata();

        }.bind(this);
        ////////////////////////////////清除结点函数////////////////////////////////////////
        this.clearGrid = function() {
            if (!ishunluan()) {
                return false;
            }
            var obj = this;

            bootbox.confirm({
                size: "small",
                message: "确定清空本地节点吗?",
                callback: function(result) {
                    if (result == true) {
                        obj.grid.removeAll();
                        obj.serializedData.splice(0, obj.serializedData.length);
                        obj.serializedData2.splice(0, obj.serializedData2.length);
                        fill('', '', '', '', '', '', '', '');
                        obj.isModify = true;
                    }
                }
            })
        }.bind(this);



        //////////////////增加结点函数//////////////////////////////
        this.addGrid = function() {
            if (!ishunluan()) {
                return false;
            }
            var id = new Date().getTime().toString();
            var obj2 = {
                id: id,
                lname: '',
                college: '',
                plantingContent: '',
                landArea: 0,
                buildingArea: 0,
                Afford: 0,
                bid: parseInt($('#load-grid').children('option:selected').val()),
                img: ''
            };
            this.serializedData2.push(obj2);
            fill(id, '', '', '', 0, 0, 0, '');

            var obj = $(".gay");
            obj.removeClass("gay");
            obj.addClass(obj.attr("id"));

            this.grid.addWidget($('<div><div class="grid-stack-item-content normal" id="normal"><div class="lname"></div><button type="button" class="close" ><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button></div><div></div></div>'),
                0, 0, 1, 1, true, 1, 4, 1, 4, id);
            var obj = {
                x: parseInt($('.gay').parent().attr('data-gs-x')),
                y: parseInt($('.gay').parent().attr('data-gs-y')),
                width: 1,
                height: 1,
                id: id
            };
            this.serializedData.push(obj);
            $('#tudi_name')[0].focus();
            this.isModify = true;
            return false;
        }.bind(this);

        /////////////////////保存结点信息//////////////////////////////////////
        this.savearray = function() {
            if (!ishunluan()) {
                return false;
            }
            var id = $('#tudi_id').val();
            if (id == '') {
                bootbox.alert({
                    message: "请先选择左侧节点",
                    size: 'small'
                });
                return false;
            }

            var n = _.findIndex(this.serializedData2, 'id', id);
            if (n < 0) {
                bootbox.alert({
                    message: "无此节点，操作失败",
                    size: 'small'
                });
                return false;
            }
            if ($('#tudi_name').val() == '') {
                bootbox.alert({
                    message: "土地名称为必填项， 请补充！",
                    size: 'small'
                });
                $('#tudi_name')[0].focus();
                return false;
            }
            if ($('#tudi_aptCollege').val() == '') {
                bootbox.alert({
                    message: "推荐学院为必选项， 请补充！",
                    size: 'small'
                });
                $('#tudi_aptCollege')[0].focus();
                return false;
            }
            this.serializedData2[n].lname = $('#tudi_name').val();
            this.serializedData2[n].college = $('#tudi_aptCollege').val();
            this.serializedData2[n].plantingContent = $('#tudi_plantingContent').val();
            this.serializedData2[n].landArea = $('#tudi_landArea').val();
            this.serializedData2[n].buildingArea = $('#tudi_buildingArea').val();
            this.serializedData2[n].Afford = $('#tudi_Afford').val();
            this.serializedData2[n].img = $('#imghead').attr("src");
            $('.gay > .lname').html($('#tudi_name').val());
            $(".gay").attr("id", aClass[$('#tudi_aptCollege').val()]);
            fuyuan();
            this.isModify = true;
        }.bind(this)

        $('#save-grid').click(this.saveGrid);
        $('#load-grid').change(this.loadGrid);
        $('#clear-grid').click(this.clearGrid);
        $('#add-grid').click(this.addGrid);
        $('#save-array').click(this.savearray);

    }; //end tudi function
}); //end ready

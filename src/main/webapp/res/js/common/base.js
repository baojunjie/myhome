define(["jquery", "jsrender"], function ($, jsrender) {
    var base = {};
    var rinteger = /^\d+$/;
    var rmobile = /^(0|86|17951)?(13[0-9]|15[012356789]|17[012356789]|18[0-9]|14[57])[0-9]{8}$/;
    var remail = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
    base.generateId = function (name) {
        var id = name + "_" + String(Math.random()).slice(2);
        return id;
    };
    base.getHtml = function (selector) {
        if (!selector) {
            return "";
        }
        if (selector instanceof jQuery) {
            return selector;
        }
        var s = selector.charAt(0);
        if (s === "." || s === "#") {
            return $(selector).html();
        }
        else {
            return selector;
        }
    };
    $.fn.extend({
        demension: function () {
            var s = { width: this.width(), height: this.height() };
            return s;
        }
    });
    // jsrender
    $.views.helpers({
        siteConfig: siteConfig,
        filterLine: function (content, newContent) {
            if (!content) {
                return "";
            }
            return content.replace(/<\/?br\s?\/?>/g, newContent || " ");
        },
        formatDate: function (unixTime, formatType) {
            if (!unixTime) {
                return "";
            }
            var d = new Date();
            unixTime = String(unixTime);
            if (unixTime.length === 10) {
                unixTime += "000";
            }
            d.setTime(unixTime);
            return d.format(formatType);
        },
        getFileUrl: function (path) {
            return siteConfig.fileUrl + path;
        },
        cut: function (s, len) {
            if (!s) {
                return "";
            }
            len = len || 10;
            if (s.length > len) {
                s = s.slice(0, len) + "...";
            }
            return s;
        }
    });

    // 兼容 image natural size 属性
    (function () {
        var props = ['Width', 'Height'], prop;
        while (prop = props.pop()) {
            (function (natural, prop) {
                $.fn[natural] = (natural in new Image()) ?
                function () {
                    return this[0][natural];
                } :
                function () {
                    var
                    node = this[0],
                    img,
                    value;

                    if (node.tagName.toLowerCase() === 'img') {
                        img = new Image();
                        img.src = node.src,
                        value = img[prop];
                    }
                    return value;
                };
            }('natural' + prop, prop.toLowerCase()));
        }
    }());

    // validate
    base.isInteger = function (v) {
        return rinteger.test(v);
    };
    base.isMobile = function (v) {
        return rmobile.test(v);
    };
    base.isEmail = function (v) {
        return remail.test(v);
    };
    base.isChinese = function (v) {
        return /^[\u4e00-\u9fa5]*$/.test(v);
    };
    base.isEnglishName = function (v) {
        return /^([a-z]+\s?)+$/i.test(v);
    };
    base.isName = function (v) {
        return base.isChinese(v) || base.isEnglishName(v);
    };
    base.isBankCard = function (v) {
        return /^\d{19}$/.test(v);
    };
    base.isIdCard = function (idcard) {
        var area = {
            11: "北京", 12: "天津", 13: "河北", 14: "山西", 15: "内蒙古", 21: "辽宁", 22: "吉林", 23: "黑龙江", 31: "上海", 32: "江苏", 33: "浙江", 34: "安徽", 35: "福建", 36: "江西", 37: "山东", 41: "河南", 42: "湖北", 43: "湖南", 44: "广东", 45: "广西", 46: "海南", 50: "重庆", 51: "四川", 52: "贵州", 53: "云南", 54: "西藏", 61: "陕西", 62: "甘肃", 63: "青海", 64: "宁夏", 65: "xingjiang", 71: "台湾", 81: "香港", 82: "澳门", 91: "国外"
        };
        var Y, JYM;
        var S, M;
        var idcard_array = new Array();
        idcard = idcard.toUpperCase();
        idcard_array = idcard.split("");
        //地区检验 
        if (area[parseInt(idcard.substr(0, 2))] == null) {
            return false;
        } else {
            //身份号码位数及格式检验 
            switch (idcard.length) {
                case 15:
                    if ((parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0 || ((parseInt(idcard.substr(6, 2)) + 1900) % 100 == 0 && (parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0)) {
                        ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/; //测试出生日期的合法性 
                    } else {
                        ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/; //测试出生日期的合法性 
                    }
                    if (!ereg.test(idcard)) {
                        return false;
                    }
                    break;
                case 18:
                    //18位身份号码检测 
                    //出生日期的合法性检查   
                    //闰年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9])) 
                    //平年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8])) 
                    if (parseInt(idcard.substr(6, 4)) % 4 == 0 || (parseInt(idcard.substr(6, 4)) % 100 == 0 && parseInt(idcard.substr(6, 4)) % 4 == 0)) {
                        ereg = /^[1-9][0-9]{5}(19|20)[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/; //闰年出生日期的合法性正则表达式 
                    } else {
                        ereg = /^[1-9][0-9]{5}(19|20)[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/; //平年出生日期的合法性正则表达式 
                    }
                    if (ereg.test(idcard)) {//测试出生日期的合法性 
                        //计算校验位 
                        S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7
                    + (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9
                    + (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10
                    + (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5
                    + (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8
                    + (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4
                    + (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2
                    + parseInt(idcard_array[7]) * 1
                    + parseInt(idcard_array[8]) * 6
                    + parseInt(idcard_array[9]) * 3;
                        Y = S % 11;
                        M = "F";
                        JYM = "10X98765432";
                        M = JYM.substr(Y, 1); //判断校验位 
                        if (M != idcard_array[17]) {
                            return false;
                        }
                    } else {
                        return false;
                    }
                    break;
                default:
                    return false;
                    break;
            }
        }
        return true;
    };

    // 反序列化
    base.deserialize = function (objectString) {
        var keys = {};
        var e, k, v,
            r = /([^&=]+)=?([^&]*)/g,
            a = /\+/g,
            d = function (s) {
                return decodeURIComponent(s.replace(a, " "));
            },
            param = objectString;

        var ch = param.charAt(0);
        if (ch === '?' || ch === '#') {
            param = param.slice(1);
        }
        while (e = r.exec(param)) {
            k = d(e[1]);
            v = d(e[2]);
            keys[k] = v;
        }
        return keys;
    }

    // 格式化
    base.format = function (s, args) {
        if (arguments.length === 0) {
            return "";
        }
        var str = s;
        for (var i = 1, len = arguments.length; i < len; i++) {
            var re = new RegExp('\\{' + (i - 1) + '\\}', 'gm');
            str = str.replace(re, arguments[i]);
        }
        return str;
    };

    if (!Array.prototype.indexOf) {
        Array.prototype.indexOf = function (searchElement, fromIndex) {
            var k;
            if (this == null) {
                throw new TypeError('"this" is null or not defined');
            }
            var O = Object(this);
            var len = O.length >>> 0;
            if (len === 0) {
                return -1;
            }
            var n = +fromIndex || 0;
            if (Math.abs(n) === Infinity) {
                n = 0;
            }
            if (n >= len) {
                return -1;
            }
            k = Math.max(n >= 0 ? n : len - Math.abs(n), 0);
            while (k < len) {
                if (k in O && O[k] === searchElement) {
                    return k;
                }
                k++;
            }
            return -1;
        };
    };

    // Date
    // 格式化为日常日期
    // 默认：yyyy-MM-dd hh:mm:ss
    Date.prototype.format = function (format) {
        if (!format) {
            format = "yyyy-MM-dd hh:mm:ss";
        }
        var o = {
            "M+": this.getMonth() + 1, //month    
            "d+": this.getDate(),    //day    
            "h+": this.getHours(),   //hour    
            "m+": this.getMinutes(), //minute    
            "s+": this.getSeconds(), //second    
            "q+": Math.floor((this.getMonth() + 3) / 3), //quarter    
            "S": this.getMilliseconds() //millisecond    
        }
        if (/(y+)/.test(format)) {
            format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
        for (var k in o) {
            if (new RegExp("(" + k + ")").test(format)) {
                format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
            }
        }
        return format;
    };
    // 格式化为日期 yyyy-MM-dd
    Date.prototype.formatDate = function () {
        return this.format("yyyy-MM-dd");
    };
    // 格式化为时间 hh:mm:ss
    Date.prototype.formatTime = function () {
        return this.format("hh:mm:ss");
    };

    return base;
});
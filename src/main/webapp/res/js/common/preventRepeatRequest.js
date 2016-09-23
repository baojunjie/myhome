/// <reference path="_references.js" />

// 阻止重复请求
define(["md5"], function (md5) {
    var preventRepeatRequest = {
        key: "site.request",
        prevent: function (key) {
            key = md5(key);
            var item = {
                time: new Date().getTime(),
                key: key
            };
            var cachedItem = this.dataManager.find(key);
            if (cachedItem) {
                var now = new Date().getTime();
                if (now - cachedItem.data.time < 2000) {
                    //console.log("2秒钟内忽略相同的请求");
                    return true;
                }
                cachedItem.data.time = new Date().getTime();
                this.dataManager.set(cachedItem.data);
            }
            else {
                this.dataManager.set(item);
            }
        },
        clear: function () {
            localStorage.setItem(this.key, "");
        },
        dataManager: {
            get: function () {
                var items = localStorage.getItem(preventRepeatRequest.key);
                if (items) {
                    items = JSON.parse(items);
                    return items;
                }
                else {
                    return [];
                }
            },
            set: function (item) {
                var items = this.get();
                var storedItem = this.find(item.key);
                if (storedItem) {
                    items.splice(storedItem.index, 1, item);
                }
                else {
                    items.push(item);
                }
                localStorage.setItem(preventRepeatRequest.key, JSON.stringify(items));
            },
            find: function (key) {
                var items = this.get();
                if (!key || !items.length) {
                    return null;
                }
                for (var i = 0, len = items.length; i < len; i++) {
                    var item = items[i];
                    if (item.key === key) {
                        return { index: i, data: item };
                    }
                }
                return null;
            }
        }
    };
    return preventRepeatRequest;
});
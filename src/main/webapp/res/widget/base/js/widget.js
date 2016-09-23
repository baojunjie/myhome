define(["base"], function (base) {
    var widget = {
        _data: {},
        set: function (name, w, $e) {
            var id = base.generateId(name);
            $e.attr("id", id);
            this._data[id] = w;
        },
        get: function (id) {
            return this._data[id];
        }
    };
    return widget;
});
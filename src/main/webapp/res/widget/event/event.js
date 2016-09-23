define(function () {
    var event = function (context) {
        this.context = context || this;
        this.callbacks = {};
    };
    event.prototype = {
        on: function (name, fn) {
            this.callbacks[name] = fn;
        },
        fire: function (name) {
            var fn = this.callbacks[name];
            if (fn) {
                return fn.apply(this.context, Array.prototype.slice.call(arguments, 1));
            }
        },
        get: function (name) {
            return this.callbacks[name];
        }
    };
    return event;
});
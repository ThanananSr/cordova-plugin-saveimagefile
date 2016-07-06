(function() {
    var cordovaRef = window.PhoneGap || window.Cordova || window.cordova; // old to new fallbacks

    function Base64ToPNG() { }

    Base64ToPNG.prototype.saveImage = function(b64String, params, win, fail) {
        cordovaRef.exec(win, fail, "Base64ToPNG", "saveImage", [b64String, params]);
    };

    cordovaRef.addConstructor(function() {
        if (!window.plugins) {
            window.plugins = {};
        }
        if (!window.plugins.base64ToPNG) {
            window.plugins.base64ToPNG = new Base64ToPNG();
        }
    });

})(); 

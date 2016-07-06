var exec = require('cordova/exec');

var Base64ToPNG =
{
    saveImage : function( b64String, params, success, error )
    {
        exec(success, error, "Base64ToPNG", "saveImage", [b64String, params])
    }
}

module.exports = Base64ToPNG;
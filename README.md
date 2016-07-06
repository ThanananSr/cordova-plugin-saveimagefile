# cordova-plugin-saveimagefile

#Add Plugin Save Image File
cordova plugin add https://github.com/ThanananSr/cordova-plugin-saveimagefile.git

#Remove Plugin Save Image File
cordova plugin rm com.spt.cordova.Base64ToPNG

#Example Using In Html
var canvas = $("#qrcode > canvas");<br/>
var url = canvas[0].toDataURL("image/jpeg");_

window.base64ToPNG.saveImage(url, {filename:"dot.jpeg", overwrite: true}, _
   function(result) {_
      alert(result);_
   }, function(error) {_
      alert(error);_
});_

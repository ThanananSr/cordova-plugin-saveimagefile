# cordova-plugin-saveimagefile

#Add Plugin Save Image File Cordova
cordova plugin add https://github.com/ThanananSr/cordova-plugin-saveimagefile.git

#Remove Plugin Save Image File Cordova
cordova plugin rm com.spt.cordova.Base64ToPNG

#Example Using In Html
var canvas = $("#qrcode > canvas"); //<br/>
var url = canvas[0].toDataURL("image/jpeg");//<br/>

window.base64ToPNG.saveImage(url, {filename:"dot.jpeg", overwrite: true}, //<br/>
   function(result) {//<br/>
      alert(result);//<br/>
   }, function(error) {//<br/>
      alert(error);//<br/>
});//

#Credit
http://stackoverflow.com/questions/11388018/phonegap-pluginhow-to-convert-base64-string-to-a-png-image-in-android

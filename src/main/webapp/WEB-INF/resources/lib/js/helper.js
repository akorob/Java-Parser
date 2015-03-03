/**
 * Created by Andrey on 26.12.2014.
 */
function log(s) {
    console.log(s);
   /* var d = new Date();
    if (typeof s == "object") {
        try {
            s = JSON.stringify(s);
        } catch (e) {
            printLn('˅˅˅˅˅ DATA ˅˅˅˅˅');
            console.log(s);
            printLn('˄˄˄˄˄ DATA ˄˄˄˄˄');
        }
    }
    else if (typeof s == "undefined") { s = ''; }
    function f(ss) { if (ss < 10) return '0' + ss; return ss; }
    function ff(ss) { if (ss < 10) return '00' + ss;  if (ss < 100) return '0' + ss;  return ss; }
    function printLn(txt) { console.log(f(d.getHours()) + ':' + f(d.getMinutes()) + ':' + f(d.getSeconds()) + '.' + ff(d.getMilliseconds()) + " >> " + txt); }
    printLn(s);*/
}

Array.prototype.contains = function(obj) {
    var i = this.length;
    while (i--) {
        if (this[i] === obj) {
            return true;
        }
    }
    return false;
}
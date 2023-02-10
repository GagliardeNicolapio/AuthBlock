
if(typeof account === 'undefined') {
    var account;
    getAccount().then((result)=>account=result)
}

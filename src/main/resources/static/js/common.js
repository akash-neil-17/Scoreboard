// Password Visibility-Sign In
$('#passwordReveal').on('click',function(){
    const type = $('#floatingPassword').attr('type') === 'password' ? 'text' : 'password';
    $('#floatingPassword').attr('type', type);
    if(type === 'password'){
        $('.passwordIcon-hide').show();
        $('.passwordIcon-show').hide();
    }
    else if (type === 'text'){
        $('.passwordIcon-show').show();
        $('.passwordIcon-hide').hide();
    }
});
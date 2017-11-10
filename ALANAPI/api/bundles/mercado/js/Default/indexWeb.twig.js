$(document).ready(function () {
    $('#registrarse').click(function () {
        $('#registroUsuario').load("{{path('web_registro')}}", function (response, status, xhr) {
        });
    });
        /*$('#contactanos').click(function () {
        console.log(1);
        $('#modalContactanos').load("{{path('web_contacto')}}", function (response, status, xhr) {
            $('#modalContacto').modal('show');
        });
    });*/
    $('.middle-first').css({'float': 'left'});
    $('#formSubmit').submit(function (e) {
        if ($('#password').val() != $('#confirmarPassword').val()) {
            $('#mensajeRegistrarse').html('<br><div class="alert alert-block fade in alert-warning">No coincide el password, vuelva a intentarlo.</div>');
            $('#password').val('');
            $('#confirmarPassword').val('');
            e.preventDefault();
        }
    });
    var usuario = '{{usuario}}';
    if (usuario == '') {
        $('#myModal88').modal('show');
    }
    //start-smooth-scrolling 
    $('.example1').wmuSlider();
    $(".scroll").click(function (event) {
        event.preventDefault();
        $('html,body').animate({scrollTop: $(this.hash).offset().top}, 1000);
    });
    $('#loginRegister').click(function () {
        if ($('.tab-2.resp-tab-content').hasClass('resp-content-active')) {
            $('.tab-2.resp-tab-content').removeClass('resp-content-active');
            $('h2[aria-controls="tab_item-0"]').click();
        }
        if (!$('.tab-1.resp-tab-content').hasClass('resp-content-active')) {
            $('.tab-1.resp-tab-content').addClass('resp-content-active');
            $('.tab-1.resp-tab-content').css('display', 'block');
        }
        $('#mensajeError').remove();
        $('#mensaje').remove();
    });
    $('#registrarse').click(function () {
        $('h2[aria-controls="tab_item-1"]').click();
        $('.tab-2.resp-tab-content').addClass('resp-content-active');
        $('.tab-1.resp-tab-content').removeClass('resp-content-active');
    });
    $('#horizontalTab').easyResponsiveTabs({
        type: 'default', //Types: default, vertical, accordion           
        width: 'auto', //auto or any width like 600px
        fit: true   // 100% fit in a container
    });
    $('.popup-with-zoom-anim').magnificPopup({
        type: 'inline',
        fixedContentPos: false,
        fixedBgPos: true,
        overflowY: 'auto',
        closeBtnInside: true,
        preloader: false,
        midClick: true,
        removalDelay: 300,
        mainClass: 'my-mfp-zoom-in'
    });
});



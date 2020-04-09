/** Ajuste do layout baseado no tamanho da tela*/
function sizeOfThings(){
    
    var windowWidth = window.innerWidth;
    var windowHeight = window.innerHeight;    
    
    
    /* Textos do Header */
    if(windowWidth < 750) {
        $('#descPlib').hide();
        $('#columnHeader').attr('class', 'col-6');
        $('#mini-menu').attr('class', 'col-2');
        $('#divExit').attr('style', 'padding: 13px 0px 0px 40px;');
        $('#titleHeader').attr('style', 'font-size: 30px;');
    } else {
        $('#descPlib').show();
        $('#columnHeader').attr('class', 'col-7');
        $('#mini-menu').attr('class', 'col-1');
        $('#divExit').attr('style', 'padding: 43px 0px 0px 0px;');
        $('#titleHeader').attr('style', 'font-size: 55px;');
    }

    /* Textos do Footer */
    if(windowWidth < 750) {
        $('#descVersion').hide();
        $('#descFooter').attr('class', 'col-12');
    } else {
        $('#descVersion').show();
        $('#descFooter').attr('class', 'col-8');
    }
    
    /* Ajusta a barra de menu (navbar) */
    if(windowWidth < 870) {
        $('#navbarUL').attr('class', 'navbar-nav mr-auto');
        $('#navbarUL').attr('style', 'padding-left: 10px;');
    } else {
        $('#navbarUL').attr('class', 'navbar-nav mr-auto');
        $('#navbarUL').attr('style', '');
    }
    
};

window.addEventListener('resize', function(){
    sizeOfThings();
});

window.addEventListener('load', function(){
    sizeOfThings();
});

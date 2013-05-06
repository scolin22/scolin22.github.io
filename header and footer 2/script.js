$(document).ready(function() {
  $('.mobile-menu-link').click(function(event) {
    event.preventDefault();
    var block = $('#block-menu-block-6');
    if (block.hasClass('open')) {
      block.removeClass('open');
      $('ul li.tmp', block).remove();
    }
    else {
      $('ul', block).prepend('<li class="tmp"><a href="/">Home</a></li>');
      block.addClass('open');
    }
  });
});
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * 
 * https://bootsnipp.com/snippets/N6BgR
 * 
 */

$(function(){
    $(".heading-compose").click(function() {
      $(".side-two").css({
        "left": "0"
      });
    });

    $(".newMessage-back").click(function() {
      $(".side-two").css({
        "left": "-100%"
      });
    });
});

var $searchBox = $('#searchText');
var $userDivs = $('.row .sideBar .sideBar-body');

$searchBox.on('input', function() {
  var scope = this;
  if (!scope.value || scope.value == '') {
    $userDivs.show();
    return;
  }

  $userDivs.each(function(i, div) {
    var $div = $(div);
    var $text = $div.find('.sideBar-name').first().text().toLowerCase();
    $div.toggle($text.indexOf(scope.value.toLowerCase()) > -1);
  });
});

// google map
var map = '';
var center;

function initialize() {

	var myLatlng = new google.maps.LatLng(-34.6556194,-58.5100765);

    var mapOptions = {
      zoom: 16,
      center: myLatlng,
      scrollwheel: false
    };
  
	var colores = [
		{
			featureType: "all",
			elementType: "all",
			stylers: [
				{ saturation: -100 }
			]
		}
	];
 
	var estilo = new google.maps.StyledMapType(colores);
    var map = new google.maps.Map(document.getElementById('map-canvas'),  mapOptions);
	var marker = new google.maps.Marker({
		position: myLatlng,
		title:"Timoteo Gordillo 1755"
	});
	map.mapTypes.set('map-canvas', estilo);
	map.setMapTypeId('map-canvas');
	    
	google.maps.event.addDomListener(map, 'idle', function() {
        calculateCenter();
    });
  
    google.maps.event.addDomListener(window, 'resize', function() {
        map.setCenter(center);
    });

	marker.setMap(map);

}

function calculateCenter() {
}

function loadGoogleMap(){
    var script = document.createElement('script');
    script.type = 'text/javascript';
    script.src = 'https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&' + 'callback=initialize';
    document.body.appendChild(script);
}

//Google map
$(function(){
  $('.flexslider').flexslider({
    controlNav : false,
    nextText : '',
    prevText : '',
    });
  loadGoogleMap();
});

function color(id){
	$('#algo').click();
}

// Hide mobile menu after clicking on a link
    $('.navbar-collapse a').click(function(){
        $(".navbar-collapse").collapse('hide');
    });

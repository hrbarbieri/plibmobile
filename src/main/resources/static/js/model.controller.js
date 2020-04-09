app.controller('findModel', function($scope, $http) {
	
	$scope.carriers = [];
	$scope.models = [];
	
	
	/* Busca uma lista de fabricantes por pais */
	$scope.findCarrierByCountry = function() {
	
		$http.get("/model/listarCarriers?countryId=281")
			.then(
				function (response) {
					
					if(response.status != "200") {
						alert("ERROR");
						return false;
					} 
					
					//console.log(response.data)
					
					$scope.carriers = response.data;
					
				});
	};
	
	
	
	$scope.findModelByCarrier = function() {
		
		$scope.models = [];
		
		var currentCarrier = $('#currentCarrier').val();
		$('#loadModel').show();
		
		$http.get("/model/listarModels?carrierId="+currentCarrier)
			.then(
				function (response) {
					
					if(response.status != "200") {
						alert("ERROR");
						return false;
					} 
					
					//console.log(response.data)
					
					$('#loadModel').hide();
					$scope.models = response.data;
					
				});
	};
	
	
	$scope.defineCorrectModelId = function () {
		$('#correctModelId').val($('#currentModel').val());
		
		//console.log('currentModel', $('#currentModel').val());
		//console.log('correctModelId', $('#correctModelId').val());
		
		$('#modal-modelo').modal('hide');
	};
	
	
	$('#currentModel').on('change', function(e){
		if(e.target.value == '-1') {
			$('.btnConfirm').attr('disabled','disabled');
		} else {
			$('.btnConfirm').removeAttr('disabled');
		}
	});

	
});


$('#currentCarrier').change(function(){
	angular.element($('.contentScope')).scope().findModelByCarrier();
});


/* Event Open */
$('#modal-modelo').on('show.bs.modal', function (e) {
	// Chama metodo do angular 'model.controller.js'
	
	console.log(angular.element($('.contentScope')).scope());
	
	angular.element($('.contentScope')).scope().findCarrierByCountry();
	$('.btnConfirm').attr('disabled','disabled');
	
});


/* Event Close */					
$('#modal-modelo').on('hide.bs.modal', function (e) {
	angular.element($('.contentScope')).scope().carriers = [];
	angular.element($('.contentScope')).scope().models = [];
	
	//console.log('correctModelId', $('#correctModelId').val())
	
	if($('#correctModelId').val() == "") {
		$('#model-correct').bootstrapSwitch('state', true);
	}
	
});



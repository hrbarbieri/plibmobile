app.controller('MainCtrl', function($scope, $http, $location) {


	// URI do APP definido no layout.html -> line:27
	var uri = $('.layout-url').data('url-layout');
	console.log('uri: ', uri);


	// URI para chamadas do Ajax
	var URI_VALIDATE_IMEI = uri+'receive/validateImei/?';
	var URI_RECEIVE_SUBMIT = uri+'receive/save';


	// armazena os recebimentos
	$scope.receiveds = [];

	// Limite em dias para estar fora do SLA do transportador
	$scope.limitSla = 21;

	$scope.alertType = -1;

	$scope.disabledAll = false;

	$scope.action = "1";

	$scope.receiveForm = {
			serials : "",
			type : "true",
			provider : "Selecione"
        };


	/**
	 * Define o tipo de chamada do alert de questão
	 */
	$scope.defineAlertQuestion = function(type) {

		$scope.alertType = type;

		if(type == 0) {
			$('#alertQuestionLabel').html("Coletado como avariado ?");
		} else if(type == 1) {
			$('#alertQuestionLabel').html("Limpar Total Lidos ?");
		} else if(type == 2) {
			$('#alertQuestionLabel').html("Finalizar o processo ?");
		}

		vibrate(250);

		$('#alertQuestion').modal('show');
	};


	$scope.confirm = function() {

		var request = new Array();
		angular.forEach($scope.receiveds, function(value, key) {
			request.push(value.serialNumber + ":" + value.damage);
		});

		$('#serials').val(request.join("-"));
		$('#type').val( $('#withDamage').val());
		$('#provider').val($('#transport').val());

		console.log($scope.receiveForm);

		submitReceive();

	}

	function _success(response) {
        _clearFormData()
    }

    function _error(response) {
        console.log(response.statusText);
    }

    //Clear the form
    function _clearFormData() {
        $scope.receiveForm.serials = '';
        $scope.receiveForm.type = "true";
        $scope.receiveForm.provider = "Selecione";
    };


	/**
	 * Ação dos botões de Sim ou Não do Modal
	 *
	 * 0 = Alerta quando o IMEI possui uma divergencia entre Tiers entre a tipo de recebimento e o coletado;
	 * 1 = Alerta para limpar a lista de recebimento;
	 * 2 = Alerta para finalizar o processo.
	 *
	 */
	$scope.defineActionButton = function(action) {

		if($scope.alertType == 0) {

			console.log('Define tipo de coleta');

			var receive = {serialNumber: $scope.currentSerial, damage: action };
	    	console.log(receive);

			$scope.receiveds.push(receive);

			// Info
			$scope.printReceiveds();


		} else if($scope.alertType == 1 && action == 1) {

			// limpa o array dos recebimentos
			$scope.receiveds = [];

			console.log('Limpa a Lista');

		} else if($scope.alertType == 2 && action == 1) {

			console.log('Confirmação da Tela');

			$scope.disabledAll = true;

			$scope.confirm();

			console.log($scope.receiveds);

		}

	}

	$scope.printReceiveds = function() {
		console.log("tamanho do array: " + $scope.receiveds.length);
		console.log($scope.receiveds);
	};


	/**
	 * Ação Enter no campo do IMEI que inicia o processo
	 *
	 */
	$scope.checkIfEnterKeyWasPressed = function($event) {

		var keyCode = $event.which || $event.keyCode;

		// Pressionou Enter, ação do leitor de C.Barras
	    if (keyCode === 13) {

	    	// Renorta os closeds caso tenham sido removidos pelos alert temp
	    	$('#alertError_btClose').show();
			$('#alertError_iconClose').show();

	    	$scope.currentSerial = $event.target.value;

	    	// Enter com o campo vazio
	    	if($scope.currentSerial == "" || $scope.currentSerial == null) {
	    		return ;
	    	}


	    	/* Remove o item do array */
	    	if($scope.action == "0") {

	    		var index = -1;

	    		// Procura o index no array
	    		$scope.receiveds.some(function(obj, i) {
	    			  return obj.serialNumber == $scope.currentSerial ? index = i : false;
	    			});

	    		console.log(index);

	    		if(index != -1) {

	    			console.log('Removido!');

		    		$scope.receiveds.splice(index, 1);
		    		alertModal("","<h5>Item<br> <strong>" + $scope.currentSerial + "</strong><br> removido.</h5>", true);
	    		} else {

	    			console.log('Item não encontrado.');
	    			alertModal("","<h3>Item não encontrado.</h3>", true);
	    		}

	    		// Info
				$scope.printReceiveds();

	    		if($scope.receiveds.length <= 0) {
	    			$scope.action = "1";
	    		}

	    		$event.target.value = "";
	    		return;
	    	}


	    	// Verifica se o item é duplicado
	    	var testDuplicate = $scope.receiveds.filter(function(item) {
    			return item.serialNumber == $scope.currentSerial;
    		});

    		if(testDuplicate != "") {

    			alertModal("<h3>Imei Duplicado</h3>","O Item: <strong>" + $scope.currentSerial + "</strong> já está na lista.", false);
    			console.log("Imei Duplicado");

    			$event.target.value = "";
    	    	$event.target.focus();

    			return;
    		}

	    	// Selecao do tipo de recebimento
	    	var damage = document.getElementById("withDamage");



	    	$('#serialNumber').attr('disabled', 'disabled');
	    	$('#loadImei').show();

	    	// Chamada no Controller
	    	$http.get(URI_VALIDATE_IMEI+"serialNumber=" + $scope.currentSerial + "&damage=" + damage.value)
	        .then(
	    	    function (response) {

	    	    	$('#serialNumber').removeAttr('disabled');
	    	    	$('#loadImei').hide();

	    	    	// Atribuindo o Obj response
	    	    	var result = response.data;


	    	    	// Caso não apresente erro
	    	    	if((result.validAccessKey == '' || result.validAccessKey == '1') && (result.messageError == null || result.messageError == '')) {

	    	    		if(result.damage == 1) {

	    	    			$scope.defineAlert = {
	    	    				sla : 	result.sla,
	    	    				outPortfolio : result.serviceOrderDto.outPortfolio
	    	    			};

	    	    			if(result.serviceOrderDto.outPortfolio == 1) {
	    	    				alertModal("<h3>URGENTE</h3>","FORA DE PORTFOLIO.", false);
	    	    			} else {
	    	    				$scope.defineAlertQuestion(0);
	    	    			}

	    	    		} else {

	    	    			if(result.serviceOrderDto.outPortfolio == 1) {
	    	    				alertModal("<h3>URGENTE</h3>","FORA DE PORTFOLIO.", false);
						  	} else if(parseInt(result.sla) > $scope.limitSla) {
		    	    			alertModal("<h3>URGENTE</h3>","ENVIAR ESTE APARELHO <h3>IMEDIATAMENTE</h3>PARA A LINHA.", false);
						  	}

	    	    			// Objeto do tipo de recevimento
	    	    	    	var receive = {serialNumber: $scope.currentSerial, damage: 0 };
	    	    	    	console.log(receive);

	    	    			$scope.receiveds.push(receive);

	    	    			// Info
	    	    			$scope.printReceiveds();
		    	    	}

	    	    	} else {
    	    			alertModal("", "<h5>"+result.messageError+"</h5>", true);
	    	    	}

	    	    	$event.target.value = "";
	    	    	console.log(response.data);

	    	    	// Foco no campo IMEI
	    	    	$event.target.focus();
	    	    }, function(response) {

	    	    	if(response.status == "408") {

	    	    		alertModal2("<h3>TIMEOUT</h3>","SUA SESSÃO EXPIROU<br>EFETUE O LOGIN NOVAMENTE...", true, 6000);

	    	    		setTimeout(function(){
	    	    			window.location.href="/logout";
	    	    		}, 6000)

	    	    	}

	    	    	console.log('ERROR');
	    	        //$scope.content = "Algo deu errado";
	    	    });

	    }
	};


	/**
	 * Acionado quando o Modal é fechado
	 */
	$('#alertQuestion').on('hidden.bs.modal', function (e) {

		console.log('alertQuestion')

		console.log("scope.alertType")
		console.log($scope.alertType)

		if($scope.alertType == 0) {

			console.log("SLA");
			console.log($scope.limitSla);
			console.log($scope.defineAlert.sla);

			if(parseInt($scope.defineAlert.sla) > $scope.limitSla) {
				alertModal("<h3>URGENTE</h3>","ENVIAR ESTE APARELHO <h3>IMEDIATAMENTE</h3>PARA A LINHA.", false);
			}

		}

		$('#serialNumber').focus();

	});


	/**
	 * Acionado quando o Modal é fechado
	 */
	$('#alertError').on('hidden.bs.modal', function (e) {
		console.log("Fechando Modal");
		$('#serialNumber').focus();
	});

});

function hideModal(milliseconds, modalName) {
	setTimeout(function(){
		$('#'+modalName).modal('hide');
	}, milliseconds);
}

/**
 * Define valores do modal
 *
 * @param title
 * @param message
 * @returns
 */
function alertModal(title, message, hideBtClose) {
	$('#alertErrorLabel').html(title);
	$('#messageModal').html(message);

	vibrate(500);
	$('#alertError').modal('show');

	if(hideBtClose) {

		hideModal(2000, "alertError");
		$('#alertError_btClose').hide();
		$('#alertError_iconClose').hide();

	}
}

function alertModal2(title, message, hideBtClose, milliseconds) {
	$('#alertErrorLabel').html(title);
	$('#messageModal').html(message);

	vibrate(500);
	$('#alertError').modal('show');

	if(hideBtClose) {

		hideModal(milliseconds, "alertError");
		$('#alertError_btClose').hide();
		$('#alertError_iconClose').hide();

	}
}

function vibrate(milliseconds) {
	console.log("vibrate: " + milliseconds);
	try {
		navigator.vibrate(milliseconds);
	} catch(e) {
	  console.log(e);
	}
}


/* Foco no campo imei */
$('#serialNumber').focus();

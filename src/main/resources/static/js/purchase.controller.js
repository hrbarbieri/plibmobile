app.controller('purchase', function($scope, $http) {
	
	// URI do APP definido no layout.html -> line:27
	var uri = $('.layout-url').data('url-layout');
	console.log('uri: ', uri);
	
	
	// URI para chamadas do Ajax
	var URI_SEARCH_IMEI = uri+'purchase/searchImei/?';
	var URI_SEARCH_QUESTIONS = uri+'purchase/questions/?';
	var URI_VALIDATE_BB3 = uri+'purchase/validateBB3/?';
	
	
	// Define a 1ª Tela
	$scope.purchaseStep = '1';
	
	$scope.disabledAll = false;
	$scope.cancelSub = false;
	$scope.lastSelectedId = null;
	$scope.currentOrder = null;
	
	// IDs da selecao do GC
	$scope.purchaseGradeIds=[];	
	
	
	// Header do GC
	$scope.showLoaded = {
		project : '',
		imei : '',
		model : ''
	};
	
	
	/**
	 * Ação Enter no campo do IMEI que inicia o processo
	 */
	$scope.checkIfEnterKeyWasPressed = function($event) {
		
		var keyCode = $event.which || $event.keyCode;
		
		// Pressionou Enter, ação do leitor de C.Barras
	    if (keyCode === 13) {
	    	$scope.searchSerialNumber();
	    }
	};
	
	
	/**
	 * Call -> Ajax
	 * Recupera dados iniciais do serial
	 */
	$scope.searchSerialNumber = function() {
		
		// Enter com o campo vazio
    	if($scope.currentSerial == "" || $scope.currentSerial == null) {
    		return ;
    	}
    	
    	$('#serialNumber').attr('disabled', 'disabled');
    	$('#loadImei').show();
    	$scope.disabledAll = true;
    	
    	// Chamada no Controller 
    	$http.get(URI_SEARCH_IMEI+"serialNumber=" + $scope.currentSerial)
        .then(
    	    function (response) {
    	    	
    	    	var result = response.data;
    	    	
    	    	if(result.message == "") {
    	    		
    	    		var serviceOrderDto = result.obj; 
    	    		$scope.findQuestions(serviceOrderDto);
    	    		
    	    		$scope.currentOrder = serviceOrderDto;
    	    		
    	    	} else {
    	    		
    	    		$('#serialNumber').removeAttr('disabled');
        	    	$('#loadImei').hide();
        	    	$scope.disabledAll = false;
        	    	
    	    		alertModal(result.title, result.message, (result.code == "0"));
    	    	}
    	    
    	    },
    	    function(errResponse){
    	        console.error('Error: ' + errResponse.data);
    	    }
        );
	};
	
	
	
	
	/**
	 * Call -> Ajax
	 * Recupera o questionario pelo serial
	 */
	$scope.findQuestions = function(serviceOrderDto) {
	
		$http.get(URI_SEARCH_QUESTIONS+"countryId=" + serviceOrderDto.countryId)
	    .then(
		    function (questions) {
		    	
		    	$('#serialNumber').removeAttr('disabled');
    	    	$('#loadImei').hide();
    	    	$scope.disabledAll = false;
    	    	
    	    	
    	    	var questions = questions.data;
    	    	
    	    	console.log('QUESTIONS: ', questions);
    	    	
    	    	var newQuestions = []; 
    	    	
    	    	
    	    	// Carrega primeiro as perguntas do grupo SALES_GRADING_FUNCTIONAL
    	    	$.each(questions, function( index, question ) {
    	    		
    	    		if(!(question.name == 'TELA' || question.name == 'CARCAÇA' || question.name == 'OUTROS')) {
    	    			
    	    			var currentCheckTypes;
    	    			var relCheck = null;
    	    			
    	    			$.each(question.checkTypes, function( index, checkTypes ) {
    	    				
    	    				
    	    				if(checkTypes.relCheck != null) {
    	    					relCheck = checkTypes.relCheck;
    	    				}
    	    				
    	    				if(question.checked == '0' && (checkTypes.desc == 'Não OK' || checkTypes.desc == 'Não')) {
    	    					currentCheckTypes = checkTypes;
    	    				} 
    	    				
    	    				
    	    				if(question.checked == '1' && (checkTypes.desc == 'Sim' || checkTypes.desc == 'OK')) {
    	    					currentCheckTypes = checkTypes;
    	    				} 

    	    			});
    	    			
    	    			newQuestions.push({
    						group: "FUNCIONAL",
    						checkId:question.id,
    						id: currentCheckTypes.id,
    	    				name: question.name,
    	    				checked: question.checked,
    	    				relCheck: relCheck,
    	    				relCheckSelected:null,
    	    				typeAnswer: currentCheckTypes.desc,
    	    				checkTypes: question.checkTypes
    	    			});
    					
    					$scope.purchaseGradeIds.push(currentCheckTypes.id);
    	    			
    	    			
    	    		}
    	    		
    	    	});
    	    	
    	    	// Carrega depois outros grupos
    	    	$.each(questions, function( index, question ) {
    	    		
    	    		if(question.name == 'TELA' || question.name == 'CARCAÇA' || question.name == 'OUTROS') {
    	    			
    	    			$.each(question.checkTypes, function( index, checkTypes ) {
    	    			
    	    				newQuestions.push({
    	    					group:question.name,
    	    					checkId:question.id,
        	    				id: checkTypes.id,
        	    				name: checkTypes.desc,
        	    				checked: '0',
        	    				relCheck: checkTypes.relCheck,
        	    				relCheckSelected:null,
        	    				typeAnswer: ""
        	    			});
    	    				
    	    			});
    	    			
    	    		}
    	    		
    	    	});
    	    	
    	    	
		    	$scope.questions = newQuestions; 
		    	
		    	
		    	
	    		// Define a 2ª Tela
	    		$scope.purchaseStep = '2';	
	    		
	    		// Define Labels
	    		$scope.showLoaded = {
	    				project : serviceOrderDto.countryName,
	    				imei : serviceOrderDto.serialNumber,
	    				model : serviceOrderDto.modelName
	    			};
	    			
	    		
	    		// Carrega as definições do component SWITCH
	    		loadConfigStepTwo(serviceOrderDto, newQuestions);
	    		
		    	console.log(newQuestions);
		    	
		    });
	};
	
	
	/**
	 * Processa as definições da seleção do SWITCH
	 */
	$scope.processAnswer = function(ids, state) {
		
		var checkId = ids.split('-')[0];
		var checkTypeId = ids.split('-')[1];
		
		$.each($scope.questions, function( index, question ) {
			
			
			if(!(question.group == 'TELA' || question.group == 'CARCAÇA' || question.group == 'OUTROS')) {
			
				if(question.id == checkTypeId) {
				
					
					var typeIdNew = 0;
					var typeIdOld = 0;
					
					
					if((question.checked == '1') != state) {
						
						// Pegar ID false 'Não' ou Não OK
						$.each(question.checkTypes, function( index, type ) {
							if(type.desc == 'Não' || type.desc == 'Não OK') {
								typeIdNew = type.id;
							}
							
							if(type.desc == 'Sim' || type.desc == 'OK') {
								typeIdOld = type.id;
							}
							
						});
							
						console.log('typeIdNew: ', typeIdNew, ' - typeIdOld: ', typeIdOld);
						
					} else if((question.checked == '1') == state) {
						
						// Pegar ID false 'SIM' ou OK
						$.each(question.checkTypes, function( index, type ) {
							if(type.desc == 'Sim' || type.desc == 'OK') {
								typeIdNew = type.id;
							}
							
							if(type.desc == 'Não' || type.desc == 'Não OK') {
								typeIdOld = type.id;
							}
							
						});
						console.log('typeIdNew: ', typeIdNew, ' - typeIdOld: ', typeIdOld);
					}
					
					
					
					// REMOVE o ID no array para a validação
					removerId(typeIdOld, question);
					
					
					// Carregar as subQuestions
					if(question.relCheck != null) {
						loadSubQuestion(ids + "|" + !state, question, null);
					} else {
						// ADICIONA o ID no array para a validação
						$scope.purchaseGradeIds.push(parseInt(typeIdNew));
					}
					
				}
				
			} else {
			
				/* 
				 * IF checklistId == question do array e a mudança da booleano for diferente do default
				 */
				if(question.id == checkTypeId && ((question.checked == '1') != state)) {
					
					
					// Carregar as subQuestions
					if(question.relCheck != null) {
						loadSubQuestion(ids + "|" + !state, question, null);
					} else {
						// ADICIONA o ID no array para a validação
						$scope.purchaseGradeIds.push(parseInt(checkTypeId));
					}
					
					
				} else if(question.id == checkTypeId && ((question.checked == '1') == state)) {
	
					// REMOVE o ID no array para a validação
					removerId(checkTypeId, question);
					
				}
			}
					
		
			
		});
	};
	
	
	/**
	 * Ação do botão MORE
	 */
	$scope.processAnswerMore = function(obj) {
		var idsWithState = obj.question.checkId +"-" + obj.question.id + "|" + (obj.question.typeAnswer == "Não OK" || obj.question.typeAnswer == "Não") 
		
		//Carrega novamente as SubQuestions com a ultima seleção
		loadSubQuestion(idsWithState, obj.question, obj.question.relCheckSelected.split('|')[2])
	};
	
	
	
	/**
	 * Onclick do radio contido no modal subQuestion
	 */
	$scope.processSubQuestion = function() {
		
		// Resolve a questão de seleções no modal
		if($scope.lastSelectedId != null) {
			removerId($scope.lastSelectedId, null);
		}
		
		
		var modal = $('#subQuestions');
		var fields = modal.find('.js-subquestion');
		
		var selectedId = null;
		
		$.each(fields, function(index, field) {
			if(field.checked) {
				selectedId = parseInt(field.value);
			}
		});
		
		
		if(selectedId != null) {
			
			$.each($scope.questions, function(index, question) {
				
				if(question.relCheck != null) {
					$.each(question.relCheck.checkTypes, function(index, checkTypes) {
						
						if(checkTypes.id == selectedId) {
							
										    // CHECKLIST_ID      CHECKLIST_TYPE.ID     REL.CHECKLIST_TYPE.ID
							var idComposto = question.checkId +"|"+question.id + "|"+selectedId;
							var idMore = question.checkId + "-" + question.id;
							
							question.relCheckSelected = idComposto;
							
							$scope.purchaseGradeIds.push(parseInt(selectedId));
							$scope.lastSelectedId = selectedId;
							
							// Ativar o icon + no questão
							var iconMore = $('#icon-more_'+ idMore);
							iconMore.removeClass('invisible');
							
							return false;
						}
						
					});
				}
				
			});
		}
		
	};
	
	
	/**
	 * Botão Cancelar do modal subQuestion
	 */
	$scope.cancelSubQuestion = function() {
		$scope.cancelSub = true;
	};
	
	
	
	/** 
	 * CLICK do botão VALIDAR
	 */
	$('.js-validate-bb3').on('click', function(e) {
		
		$('#load').modal('show');
		
		console.log('purchaseGradeIds: ', $scope.purchaseGradeIds);
		
		var orderId = $scope.currentOrder.id;
		
		
		var modelCorrect = $scope.currentOrder.modelId;
		if($('#correctModelId').val() != '') {
			modelCorrect = $('#correctModelId').val();
		}
		
		/*var voucherCorrect = $scope.currentOrder.voucher;
		if($('#correctVoucher').val() != '') {
			voucherCorrect = $('#correctVoucher').val();
		}*/
		
		var urlBBB = URI_VALIDATE_BB3+'types='+$scope.purchaseGradeIds.join('-')+"&order="+orderId+"&modelCorrect="+modelCorrect
		
		$http.get(urlBBB)
			.then(
				function(response) { // successCallback

					console.log('successCallback: ', response);
					$('#load').modal('hide');
					
					var purgraseGrade = response.data;
					
					if(!purgraseGrade.status) {
						alertModal("Erro Inexperado","<h5><strong>" + purgraseGrade.message + "</strong></h5>", false);
						return false;
					}
					
					$scope.purchaseStep = '3';
					
					
				}, 
				function(response) { // errorCallback
					console.log('errorCallback: ', response);
					$('#load').modal('hide');
					
					
					
					
				});
		
	});
	
	$('.js-validate-edit').on('click', function(e) {
		
		// Visualiza a 
		$scope.purchaseStep = '2';
		
	});
	
	
	$('#subQuestions').on('show.bs.modal',function(e){
		$scope.cancelSub = false;
	});
	

	
	/**
	 *  CLOSE do modal das Sub Questions
	 */
	$('#subQuestions').on('hide.bs.modal',function(e){
		
		$scope.lastSelectedId = null;
		
		$('.sub-question-title').html("");
		$('.list-questions').html("");
		
		var id = $scope.currentSubQuestion.split("|")[0];
		var state = $scope.currentSubQuestion.split("|")[1];
		
		var result = $.grep($scope.questions, function(e){
			if(e.id == parseInt(id.split('-')[1]))
				return e;
		});
		
		// Ao fechar o modal das subQuestions pelo botão cancelar, remove o ID
		if($scope.cancelSub) {
			removerId(null, result[0]);
			
			state = result.checked == '1';
		}
		
		if(result.length == 0 || result[0].relCheckSelected == null) {
			$('#'+id).bootstrapSwitch('state', Boolean(JSON.parse(state)));
		}
		
	});
	
	
	// CLOSE do modal alerta de erros
	$('#alertError').on('hidden.bs.modal', function (e) {
		$scope.currentSerial = "";
		$('#serialNumber').val('');
		$('#serialNumber').focus();
	});
	
	
	/*
	 * Carrega as Sub Questions
	 */
	function loadSubQuestion(currentSubQuestion, question, selectedId) {
		
		// Usado no Hide do Modal das subQuestions
		$scope.currentSubQuestion = currentSubQuestion;
		$scope.currentSubQuestionObj = question;
		
		// Define o titulo do modal SubQuestions
		$('.sub-question-title').html(question.relCheck.name);
		
		
		
		// Carrega as subQuestions
		var contentSubQuestions = $('.list-questions');
		contentSubQuestions.html('');
		
		$.each(question.relCheck.checkTypes, function(index, checkTypes) {
			
			var checked = '';
			if(selectedId != null && parseInt(selectedId) == parseInt(checkTypes.id)) {
				checked = 'checked';
				$scope.lastSelectedId = selectedId;
			}
			
			contentSubQuestions.append('<div class="radio  radio-inline">')
			contentSubQuestions.append('<input type="radio" class="js-subquestion"  '+checked+'  value="'+ checkTypes.id +'" name="subQuestion_'+question.id+'" onclick="changeIdSelected(this)" />');
			contentSubQuestions.append('<label>&nbsp;'+ checkTypes.desc +'</label></div>');
			
		});
		
		$('#subQuestions').modal('show');
		
	}
	
	
	/* Remove o ID da lista de 'purchaseGradeIds' e controla o icon MORE */
	function removerId(checkTypeId, question) {
		
		
		// Configurações visuais do icon more
		if(question != null && question.relCheckSelected != null) {
			
			var relCheckSelected = question.relCheckSelected;
		
			checkTypeId = relCheckSelected.split("|")[2];
			
			var idMore = relCheckSelected.split("|")[0] + "-" + relCheckSelected.split("|")[1];
			
			// Ativar o icon + no questão
			var iconMore = $('#icon-more_'+ idMore);
			iconMore.addClass('invisible');
			
			question.relCheckSelected = null;
		}
		
		
		if(checkTypeId != null) {
			var indexOf = $scope.purchaseGradeIds.indexOf(parseInt(checkTypeId));
			if(indexOf >=0 ) {
				$scope.purchaseGradeIds.splice(indexOf,1);
			}
		}
		
	}
	
});



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
	
	navigator.vibrate(500);
	$('#alertError').modal('show');
	
	if(hideBtClose) {
		hideModal(2500, "alertError");
		$('#alertError_btClose').hide();
		$('#alertError_iconClose').hide();
		
	}
}

function hideModal(milliseconds, modalName) {
	setTimeout(function(){
		$('#'+modalName).modal('hide');		
	}, milliseconds);
}


function changeIdSelected(field) {
	angular.element($('#correctModelId')).scope().processSubQuestion();
}


/* Foco no campo imei */
$('#serialNumber').focus();
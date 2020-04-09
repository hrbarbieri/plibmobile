$(function() {
					
	/* Event Model Correct */
	$('#model-correct').on('switchChange.bootstrapSwitch', function (event, state) {
		if(!state) {
			$('#modal-modelo').modal('show');
		} else {
			$('#correctModelId').val("");
			
			$('.new-model').html("");
			$('.newModelCorrect').attr('style','display:none');
			$('.actual-model').attr('style','display:block');
		}
	});
	
	
	$('#modal-modelo').on('hide.bs.modal', function (e) {
		if($('#correctModelId').val() != "") {
			$('.new-model').html($('#currentModel option:selected').text());
			$('.newModelCorrect').attr('style','display:');
			$('.actual-model').attr('style','display:none');
		}
	});
	
})


/**
 * VERIFICAR PQ NÃO ESTÁ CONVERTENDO AS RESPOSTAS DO SWITCH EM ok E não ok
 * 
 */
		
/*
 *	Definições iniciais do compomente SWITCH 
 */
function loadConfigStepTwo(serviceOrder, questions) {
				
	setTimeout(function() {
		
		$('.switch-change').bootstrapSwitch('size', 'mini');
		$('#model-correct').bootstrapSwitch('state', true);
	
		$.each(questions, function( index, question ) {
			
			var idSwitch = question.checkId+"-"+question.id;
			
			if(question.typeAnswer != '') {
				
				$('#'+idSwitch).bootstrapSwitch('onText', 'OK');
				$('#'+idSwitch).bootstrapSwitch('offText', 'Não OK');
			} else {
				$('#'+idSwitch).bootstrapSwitch('onText', 'Sim');
				$('#'+idSwitch).bootstrapSwitch('offText', 'Não');

			}
			
			$('#'+idSwitch).bootstrapSwitch('state', (question.checked == '1'));
			
			//Define chamadas aos "switch"							
			$('#'+idSwitch).on('switchChange.bootstrapSwitch', function (event, state) {
				angular.element($('#correctModelId')).scope().processAnswer(event.target.id, state);
			});
			
			
		});
	
	}, 100);

}
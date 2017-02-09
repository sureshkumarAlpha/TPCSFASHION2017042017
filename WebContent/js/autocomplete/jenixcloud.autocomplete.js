

(function($,undefined){

	$.fn.listGroup=function(option){
		if(option!=null){
			$(option.nameField).autocomplete(
					{
						source : function(request, response) {
							$.ajax({
								url : option.url,
								async : true,
								dataType : 'json',
								data : {
									q : $(option.nameField).val(),
									term : request.term
								},
								success : function(data) {
									response(data);
								}
							});
						},
						minLength: 0,
						select : function(event, ui) {
							if(parseInt(ui.item.id)!=-2){
								$(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
								event.stopPropagation();
								$(option.formId).bootstrapValidator('revalidateField', option.validateId);
								
							}
							else{
								$(option.nameField).val('');
								$(option.idField).val('');
								event.stopPropagation();
								createNewGroup();
							}
							return false;
						},
						 focus: function(event, ui) {
							 event.preventDefault();
							 if(parseInt(ui.item.id)!=-2){
								 $(option.nameField).val(ui.item.name);
									$(option.idField).val(ui.item.id);
								 }
								 else{
									 $(option.nameField).val('');
										$(option.idField).val('');
								 }
								
					        }
					}).data("ui-autocomplete")._renderItem = function(ul, item) {
				return $("<li></li>")
				.data("item.autocomplete", item)
				.append("<a>" +item.name+ "</a>")
				.appendTo(ul);
			};
		}			
	};
	
$.fn.listVariant=function(option){
		
		if(option!=null && $(option.nameField).val()!=undefined){
			
		$(option.nameField).autocomplete(
		{ 			 
					source : function(request, response) {
						$.ajax({
							url :option.url,
							async : true,
							dataType : 'json',
							data : {
								
								q : $(option.nameField).val(),
								term : request.term
							},
							success : function(data) {
								response(data);
							}
						});
					},
					minLength: 0,
					select : function(event, ui) {
						if(parseInt(ui.item.id)!=-2){
							$(option.nameField).val(ui.item.name);
							$(option.idField).val(ui.item.id);
							event.stopPropagation();
							 	$(option.formId).bootstrapValidator('revalidateField', option.validateId);
						}
						else{
							$( option.nameField ).val('');
							$(option.idField ).val('');
							event.stopPropagation();
							createNewVariant();
						}
						return false;
					},
					 focus: function(event, ui) {
						 event.preventDefault();
						 if(parseInt(ui.item.id)!=-2){
							 $(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
							 }
							 else{
								 $(option.nameField).val('');
									$(option.idField).val('');
							 }
							
					 },
					change: function (event, ui) {
		                if (!ui.item){
		                	 $(option.nameField).val('');
		                	 $(option.idField).val('');
		                	 $(option.formId).bootstrapValidator('revalidateField', option.validateId);
		                }
		            }
				}).data("ui-autocomplete")._renderItem = function(ul, item) {
			return $("<li></li>")
			.data("item.autocomplete", item)
			.append("<a>" +item.name+ "</a>")
			.appendTo(ul);
		};
		}
	};
	
	$.fn.listIndentType=function(option){
		if(option!=null){
			$(option.nameField).autocomplete(
					{
						source : function(request, response) {
							$.ajax({
								url : option.url,
								async : true,
								dataType : 'json',
								data : {
									q : $(option.nameField).val(),
									term : request.term
								},
								success : function(data) {
									response(data);
								}
							});
						},
						minLength: 0,
						select : function(event, ui) {
							if(parseInt(ui.item.id)!=-2){
								$(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
								event.stopPropagation();
								$(option.formId).bootstrapValidator('revalidateField', option.validateId);
								
							}
							else{
								$(option.nameField).val('');
								$(option.idField).val('');
								event.stopPropagation();
								createNewGroup();
							}
							return false;
						},
						 focus: function(event, ui) {
							 event.preventDefault();
							 if(parseInt(ui.item.id)!=-2){
								 $(option.nameField).val(ui.item.name);
									$(option.idField).val(ui.item.id);
								 }
								 else{
									 $(option.nameField).val('');
										$(option.idField).val('');
								 }
								
					        }
					}).data("ui-autocomplete")._renderItem = function(ul, item) {
				return $("<li></li>")
				.data("item.autocomplete", item)
				.append("<a>" +item.name+ "</a>")
				.appendTo(ul);
			};
		}			
	};
	
	$.fn.listTempGroup=function(option){
		if(option!=null){
			$(option.nameField).autocomplete(
					{
						source : function(request, response) {
							$.ajax({
								url : option.url,
								async : true,
								dataType : 'json',
								data : {
									q : $(option.nameField).val(),
									term : request.term
								},
								success : function(data) {
									response(data);
								}
							});
						},
						minLength: 0,
						select : function(event, ui) {
							if(parseInt(ui.item.id)!=-2){
								$(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
								event.stopPropagation();
								$(option.formId).bootstrapValidator('revalidateField', option.validateId);
								
							}
							else{
								$(option.nameField).val('');
								$(option.idField).val('');
								event.stopPropagation();
								//createNewGroup();
							}
							return false;
						},
						 focus: function(event, ui) {
							 event.preventDefault();
							 if(parseInt(ui.item.id)!=-2){
								 $(option.nameField).val(ui.item.name);
									$(option.idField).val(ui.item.id);
								 }
								 else{
									 $(option.nameField).val('');
										$(option.idField).val('');
								 }
								
					        }
					}).data("ui-autocomplete")._renderItem = function(ul, item) {
				return $("<li></li>")
				.data("item.autocomplete", item)
				.append("<a>" +item.name+ "</a>")
				.appendTo(ul);
			};
		}			
	};
	$.fn.listUOM=function(option){
		if(option!=null){
		$(option.nameField).autocomplete(
				{
					source : function(request, response) {
						$.ajax({
							url : option.url,
							async : true,
							dataType : 'json',
							data : {
								q : $(option.nameField).val(),
								term : request.term
							},
							success : function(data) {
								response(data);
							}
						});
					},
					minLength: 0,
					select : function(event, ui) {
						if(parseInt(ui.item.id)!=-2){
							$(option.nameField).val(ui.item.name);
							$(option.idField).val(ui.item.id);
							event.stopPropagation();
							$(option.formId).bootstrapValidator('revalidateField', option.validateId);
						}
						else{
							$(option.nameField).val('');
							$(option.idField).val('');
							event.stopPropagation();
							createNewUOM();
						}
						return false;
					},
					 focus: function(event, ui) {
						 event.preventDefault();
						 if(parseInt(ui.item.id)!=-2){
							 $(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
							 }
							 else{
								 $(option.nameField).val('');
									$(option.idField).val('');
							 }
							
				        }
				}).data("ui-autocomplete")._renderItem = function(ul, item) {
//			var re = new RegExp("^"+this.term) ;
//			var t = item.name.replace(re,"<span style='font-weight:bold;color:Blue;'>" +this.term +"</span>");
			return $("<li></li>")
			.data("item.autocomplete", item)
			.append("<a>" +item.name+ "</a>")
			.appendTo(ul);
		};
		}
	};
	$.fn.listInventoryAccount=function(option){
		if(option!=null){
		$(option.nameField).autocomplete(
				{
					source : function(request, response) {
						$.ajax({
							url : option.url,
							async : true,
							dataType : 'json',
							data : {
								q : $(option.nameField).val(),
								term : request.term
							},
							success : function(data) {
								response(data);
							}
						});
					},
					minLength: 0,
					select : function(event, ui) {
						if(parseInt(ui.item.id)!=-2){
							$(option.nameField).val(ui.item.name);
							$(option.idField).val(ui.item.id);
							event.stopPropagation();
							//	$('#validate-form').bootstrapValidator('revalidateField', 'basic_unit');
						}
						else{
							$( option.nameField).val('');
							$( option.idField ).val('');
							event.stopPropagation();
							createNewAccount();
						}
						return false;
					},
					 focus: function(event, ui) {
						 event.preventDefault();
						 if(parseInt(ui.item.id)!=-2){
							 $(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
							 }
							 else{
								 $(option.nameField).val('');
									$(option.idField).val('');
							 }
							
				        }
				}).data("ui-autocomplete")._renderItem = function(ul, item) {
//			var re = new RegExp("^"+this.term) ;
//			var t = item.name.replace(re,"<span style='font-weight:bold;color:Blue;'>" +this.term +"</span>");
			return $("<li></li>")
			.data("item.autocomplete", item)
			.append("<a>" +item.name+ "</a>")
			.appendTo(ul);
		};
		}
	};
	$.fn.listPurchaseAccount=function(option){
		if(option!=null){
		$(option.nameField).autocomplete(
				{
					source : function(request, response) {
						$.ajax({
							url :option.url,
							async : true,
							dataType : 'json',
							data : {
								q : $(option.nameField).val(),
								term : request.term
							},
							success : function(data) {
								response(data);
							}
						});
					},
					minLength: 0,
					select : function(event, ui) {
						if(parseInt(ui.item.id)!=-2){
							$(option.nameField).val(ui.item.name);
							$(option.idField).val(ui.item.id);
							event.stopPropagation();
							//	$('#validate-form').bootstrapValidator('revalidateField', 'basic_unit');
						}
						else{
							$( option.nameField ).val('');
							$(option.idField ).val('');
							event.stopPropagation();
							createNewAccount();
						}
						return false;
					},
					 focus: function(event, ui) {
						 event.preventDefault();
						 if(parseInt(ui.item.id)!=-2){
							 $(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
							 }
							 else{
								 $(option.nameField).val('');
									$(option.idField).val('');
							 }
							
				        }
				}).data("ui-autocomplete")._renderItem = function(ul, item) {
//			var re = new RegExp("^"+this.term) ;
//			var t = item.name.replace(re,"<span style='font-weight:bold;color:Blue;'>" +this.term +"</span>");
			return $("<li></li>")
			.data("item.autocomplete", item)
			.append("<a>" +item.name+ "</a>")
			.appendTo(ul);
		};
		}
	};
	
	$.fn.listMaterial=function(option){
		if(option!=null){
//			var offsetTop = $(option.nameField).offset().top;
//			winHt=$(window).height();
//			var myPos="left bottom";
//			var atPos="left top";
//			
//			alert('top :'+offsetTop+' scroll top :'+$(window).scrollTop());
////			
//			if((offsetTop-$(window).scrollTop())<200){
//				myPos="left top";
//				atPos="left bottom";
//			}
//			else if((winHt-offsetTop)<200){
//				myPos="left top";
//				atPos="left bottom";
//			}
			
		$(option.nameField).autocomplete(
		{ 			//position: { my: myPos, at: atPos, of: $(option.nameField),collision: "flip" },
					source : function(request, response) {
						$.ajax({
							url :option.url,
							async : true,
							dataType : 'json',
							data : {
								
								q : $(option.nameField).val(),
								group_id:$(option.dependIdField).val(),
								term : request.term
							},
							success : function(data) {
								response(data);
							}
						});
					},
					minLength: 0,
					select : function(event, ui) {
						if(parseInt(ui.item.id)!=-2){
							$(option.nameField).val(ui.item.name);
							$(option.idField).val(ui.item.id);
							event.stopPropagation();
							 	$(option.formId).bootstrapValidator('revalidateField', option.validateId);
						}
						else{
							$( option.nameField ).val('');
							$(option.idField ).val('');
							event.stopPropagation();
							createNewMaterial();
						}
						return false;
					},
					 focus: function(event, ui) {
						 event.preventDefault();
						 if(parseInt(ui.item.id)!=-2){
							 $(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
							 }
							 else{
								 $(option.nameField).val('');
									$(option.idField).val('');
							 }
							
				        }
				}).data("ui-autocomplete")._renderItem = function(ul, item) {
//			var re = new RegExp("^"+this.term) ;
//			var t = item.name.replace(re,"<span style='font-weight:bold;color:Blue;'>" +this.term +"</span>");
			return $("<li></li>")
			.data("item.autocomplete", item)
			.append("<a>" +item.name+ "</a>")
			.appendTo(ul);
		};
		}
	};
	
	$.fn.listAccountGroup=function(option){
		if(option!=null){
		$(option.nameField).autocomplete(
		{
					source : function(request, response) {
						$.ajax({
							url :option.url,
							async : true,
							dataType : 'json',
							data : {
								q : $(option.nameField).val(),
								create_new:$(option.createNew).val(),
								term : request.term
							},
							success : function(data) {
								response(data);
							}
						});
					},
					minLength: 0,
					select : function(event, ui) {
						if(parseInt(ui.item.id)!=-2){
							$(option.nameField).val(ui.item.name);
							$(option.idField).val(ui.item.id);
							event.stopPropagation();
							 	$(option.formId).bootstrapValidator('revalidateField', option.validateId);
						}
						else{
							$( option.nameField ).val('');
							$(option.idField ).val('');
							event.stopPropagation();
							createNewAccountGroup();
						}
						return false;
					},
					 focus: function(event, ui) {
						 event.preventDefault();
						 if(parseInt(ui.item.id)!=-2){
							 $(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
							 }
							 else{
								 $(option.nameField).val('');
									$(option.idField).val('');
							 }
							
				        }
				}).data("ui-autocomplete")._renderItem = function(ul, item) {
//			var re = new RegExp("^"+this.term) ;
//			var t = item.name.replace(re,"<span style='font-weight:bold;color:Blue;'>" +this.term +"</span>");
			return $("<li></li>")
			.data("item.autocomplete", item)
			.append("<a>" +item.name+ "</a>")
			.appendTo(ul);
		};
		}
	};
	
	
	$.fn.listAccount=function(option){
		if(option!=null){
		$(option.nameField).autocomplete(
		{
					source : function(request, response) {
						$.ajax({
							url :option.url,
							async : true,
							dataType : 'json',
							data : {
								q : $(option.nameField).val(),
								create_new:$(option.createNew).val(),
								term : request.term
							},
							success : function(data) {
								response(data);
							}
						});
					},
					minLength: 0,
					select : function(event, ui) {
						if(parseInt(ui.item.id)!=-2){
							$(option.nameField).val(ui.item.name);
							$(option.idField).val(ui.item.id);
							event.stopPropagation();
							 	$(option.formId).bootstrapValidator('revalidateField', option.validateId);
						}
						else{
							$( option.nameField ).val('');
							$(option.idField ).val('');
							event.stopPropagation();
							//createNewAccountGroup();
						}
						return false;
					}
					,
					 focus: function(event, ui) {
						 event.preventDefault();
						 if(parseInt(ui.item.id)!=-2){
							 $(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
							 }
							 else{
								 $(option.nameField).val('');
									$(option.idField).val('');
							 }
							
				        }
				}).data("ui-autocomplete")._renderItem = function(ul, item) {
//			var re = new RegExp("^"+this.term) ;
//			var t = item.name.replace(re,"<span style='font-weight:bold;color:Blue;'>" +this.term +"</span>");
			return $("<li></li>")
			.data("item.autocomplete", item)
			.append("<a>" +item.name+ "</a>")
			.appendTo(ul);
		};
		}
	};
	
	$.fn.listCustomer=function(option){
		if(option!=null){
			$(option.nameField).autocomplete(
					{
						source : function(request, response) {
							$.ajax({
								url : option.url,
								async : true,
								dataType : 'json',
								data : {
									q : $(option.nameField).val(),
									term : request.term
								},
								success : function(data) {
									response(data);
								}
							});
						},
						minLength: 0,
						select : function(event, ui) {
							if(parseInt(ui.item.id)!=-2){
								$(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
								event.stopPropagation();
								$(option.formId).bootstrapValidator('revalidateField', option.validateId);
							}
							else{
								$(option.nameField).val('');
								$(option.idField).val('');
								event.stopPropagation();
								 createNewCustomer();
							}
							return false;
						},
						 focus: function(event, ui) {
							 event.preventDefault();
							 if(parseInt(ui.item.id)!=-2){
								 $(option.nameField).val(ui.item.name);
									$(option.idField).val(ui.item.id);
								 }
								 else{
									 $(option.nameField).val('');
										$(option.idField).val('');
								 }
								
					        }
					}).data("ui-autocomplete")._renderItem = function(ul, item) {
				return $("<li></li>")
				.data("item.autocomplete", item)
				.append("<a>" +item.name+ "</a>")
				.appendTo(ul);
			};
		}			
	};

	
	$.fn.listAgent=function(option){
		if(option!=null){
			$(option.nameField).autocomplete(
					{
						source : function(request, response) {
							$.ajax({
								url : option.url,
								async : true,
								dataType : 'json',
								data : {
									q : $(option.nameField).val(),
									term : request.term
								},
								success : function(data) {
									response(data);
								}
							});
						},
						minLength: 0,
						select : function(event, ui) {
							if(parseInt(ui.item.id)!=-2){
								$(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
								event.stopPropagation();
								$(option.formId).bootstrapValidator('revalidateField', option.validateId);
							}
							else{
								$(option.nameField).val('');
								$(option.idField).val('');
								event.stopPropagation();
								 createNewCustomer();
							}
							return false;
						},
						 focus: function(event, ui) {
							 event.preventDefault();
							 if(parseInt(ui.item.id)!=-2){
								 $(option.nameField).val(ui.item.name);
									$(option.idField).val(ui.item.id);
								 }
								 else{
									 $(option.nameField).val('');
										$(option.idField).val('');
								 }
								
					        }
					}).data("ui-autocomplete")._renderItem = function(ul, item) {
				return $("<li></li>")
				.data("item.autocomplete", item)
				.append("<a>" +item.name+ "</a>")
				.appendTo(ul);
			};
		}			
	};
	
	$.fn.listSeason=function(option){
		if(option!=null){
			$(option.nameField).autocomplete(
					{
						source : function(request, response) {
							$.ajax({
								url : option.url,
								async : true,
								dataType : 'json',
								data : {
									q : $(option.nameField).val(),
									term : request.term
								},
								success : function(data) {
									response(data);
								}
							});
						},
						minLength: 0,
						select : function(event, ui) {
							if(parseInt(ui.item.id)!=-2){
								$(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
								event.stopPropagation();
								$(option.formId).bootstrapValidator('revalidateField', option.validateId);
							}
							else{
								$(option.nameField).val('');
								$(option.idField).val('');
								event.stopPropagation();
								 createNewCustomer();
							}
							return false;
						},
						 focus: function(event, ui) {
							 event.preventDefault();
							 if(parseInt(ui.item.id)!=-2){
								 $(option.nameField).val(ui.item.name);
									$(option.idField).val(ui.item.id);
								 }
								 else{
									 $(option.nameField).val('');
										$(option.idField).val('');
								 }
								
					        }
					}).data("ui-autocomplete")._renderItem = function(ul, item) {
				return $("<li></li>")
				.data("item.autocomplete", item)
				.append("<a>" +item.name+ "</a>")
				.appendTo(ul);
			};
		}			
	};
	
	$.fn.listSupplier=function(option){
		if(option!=null){
			$(option.nameField).autocomplete(
					{
						source : function(request, response) {
							$.ajax({
								url : option.url,
								async : true,
								dataType : 'json',
								data : {
									q : $(option.nameField).val(),
									term : request.term
								},
								success : function(data) {
									response(data);
								}
							});
						},
						minLength: 0,
						select : function(event, ui) {
							if(parseInt(ui.item.id)!=-2){
								$(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
								event.stopPropagation();
								$(option.formId).bootstrapValidator('revalidateField', option.validateId);
							}
							else{
								$(option.nameField).val('');
								$(option.idField).val('');
								event.stopPropagation();
								 createNewCustomer();
							}
							return false;
						},
						 focus: function(event, ui) {
							 event.preventDefault();
							 if(parseInt(ui.item.id)!=-2){
								 $(option.nameField).val(ui.item.name);
									$(option.idField).val(ui.item.id);
								 }
								 else{
									 $(option.nameField).val('');
										$(option.idField).val('');
								 }
								
					        }
					}).data("ui-autocomplete")._renderItem = function(ul, item) {
				return $("<li></li>")
				.data("item.autocomplete", item)
				.append("<a>" +item.name+ "</a>")
				.appendTo(ul);
			};
		}			
	};
	
	$.fn.listAccountReceivedFrom=function(option){
		if(option!=null){
			$(option.nameField).autocomplete(
					{
						source : function(request, response) {
							$.ajax({
								url : option.url,
								async : true,
								dataType : 'json',
								data : {
									q : $(option.nameField).val(),
									term : request.term
								},
								success : function(data) {
									response(data);
								}
							});
						},
						minLength: 0,
						select : function(event, ui) {
							if(parseInt(ui.item.id)!=-2){
								$(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
								$(option.typeField).val(ui.item.type);
								$(option.categoryField).val(ui.item.category);
								event.stopPropagation();
								$(option.formId).bootstrapValidator('revalidateField', option.validateId);
							}
							else{
								$(option.nameField).val('');
								$(option.idField).val('');
								$(option.typeField).val('');
								$(option.categoryField).val('');
								event.stopPropagation();
								createNewAccount();
							}
							return false;
						},
						 focus: function(event, ui) {
							 event.preventDefault();
							 if(parseInt(ui.item.id)!=-2){
								 $(option.nameField).val(ui.item.name);
									$(option.idField).val(ui.item.id);
									$(option.typeField).val(ui.item.type);
									$(option.categoryField).val(ui.item.category);
								 }
								 else{
									 $(option.nameField).val('');
										$(option.idField).val('');
										$(option.typeField).val('');
										$(option.categoryField).val('');
								 }
								
					        }
					}).data("ui-autocomplete")._renderItem = function(ul, item) {
				return $("<li></li>")
				.data("item.autocomplete", item)
				.append("<a>" +item.name+ "</a>")
				.appendTo(ul);
			};
		}			
	};
	
	$.fn.listAccountDepositTo=function(option){
		if(option!=null){
			$(option.nameField).autocomplete(
					{
						source : function(request, response) {
							$.ajax({
								url : option.url,
								async : true,
								dataType : 'json',
								data : {
									q : $(option.nameField).val(),
									term : request.term
								},
								success : function(data) {
									response(data);
								}
							});
						},
						minLength: 0,
						select : function(event, ui) {
							if(parseInt(ui.item.id)!=-2){
								$(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
								event.stopPropagation();
								$(option.formId).bootstrapValidator('revalidateField', option.validateId);
							}
							else{
								$(option.nameField).val('');
								$(option.idField).val('');
								event.stopPropagation();
								 createNewAccount();
							}
							return false;
						},
						 focus: function(event, ui) {
							 event.preventDefault();
							 if(parseInt(ui.item.id)!=-2){
								 $(option.nameField).val(ui.item.name);
									$(option.idField).val(ui.item.id);
								 }
								 else{
									 $(option.nameField).val('');
										$(option.idField).val('');
								 }
								
					        }
					}).data("ui-autocomplete")._renderItem = function(ul, item) {
				return $("<li></li>")
				.data("item.autocomplete", item)
				.append("<a>" +item.name+ "</a>")
				.appendTo(ul);
			};
		}			
	};
	$.fn.listAccountPaidTo=function(option){
		if(option!=null){
			$(option.nameField).autocomplete(
					{
						source : function(request, response) {
							$.ajax({
								url : option.url,
								async : true,
								dataType : 'json',
								data : {
									q : $(option.nameField).val(),
									term : request.term
								},
								success : function(data) {
									response(data);
								}
							});
						},
						minLength: 0,
						select : function(event, ui) {
							if(parseInt(ui.item.id)!=-2){
								$(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
								$(option.typeField).val(ui.item.type);
								$(option.categoryField).val(ui.item.category);
								event.stopPropagation();
								$(option.formId).bootstrapValidator('revalidateField', option.validateId);
							}
							else{
								$(option.nameField).val('');
								$(option.idField).val('');
								$(option.typeField).val('');
								$(option.categoryField).val('');
								event.stopPropagation();
								 createNewAccount();
							}
							return false;
						},
						 focus: function(event, ui) {
							 event.preventDefault();
							 if(parseInt(ui.item.id)!=-2){
								 $(option.nameField).val(ui.item.name);
									$(option.idField).val(ui.item.id);
									$(option.typeField).val(ui.item.type);
									$(option.categoryField).val(ui.item.category);	
								 }
								 else{
									 $(option.nameField).val('');
										$(option.idField).val('');
										$(option.typeField).val('');
										$(option.categoryField).val('');
								 }
								
					        }
					}).data("ui-autocomplete")._renderItem = function(ul, item) {
				return $("<li></li>")
				.data("item.autocomplete", item)
				.append("<a>" +item.name+ "</a>")
				.appendTo(ul);
			};
		}			
	};
	$.fn.listJournalAccount=function(option){
		if(option!=null){
			$(option.nameField).autocomplete(
					{
						source : function(request, response) {
							$.ajax({
								url : option.url,
								async : true,
								dataType : 'json',
								data : {
									q : $(option.nameField).val(),
									term : request.term
								},
								success : function(data) {
									response(data);
								}
							});
						},
						minLength: 0,
						select : function(event, ui) {
							if(parseInt(ui.item.id)!=-2){
								$(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
								$(option.typeField).val(ui.item.type);
								$(option.categoryField).val(ui.item.category);
								event.stopPropagation();
								$(option.formId).bootstrapValidator('revalidateField', option.validateId);
							}
							else{
								$(option.nameField).val('');
								$(option.idField).val('');
								$(option.typeField).val('');
								$(option.categoryField).val('');
								event.stopPropagation();
								 createNewAccount();
							}
							return false;
						},
						 focus: function(event, ui) {
							 event.preventDefault();
							 if(parseInt(ui.item.id)!=-2){
								 $(option.nameField).val(ui.item.name);
									$(option.idField).val(ui.item.id);
									$(option.typeField).val(ui.item.type);
									$(option.categoryField).val(ui.item.category);
								 }
								 else{
									 $(option.nameField).val('');
										$(option.idField).val('');
										$(option.typeField).val('');
										$(option.categoryField).val('');
								 }
								
					        }
					}).data("ui-autocomplete")._renderItem = function(ul, item) {
				return $("<li></li>")
				.data("item.autocomplete", item)
				.append("<a>" +item.name+ "</a>")
				.appendTo(ul);
			};
		}			
	};
	
	$.fn.listGroupForAccount=function(option){
		if(option!=null){
			$(option.nameField).autocomplete(
					{
						source : function(request, response) {
							$.ajax({
								url : option.url,
								async : true,
								dataType : 'json',
								data : {
									q : $(option.nameField).val(),
									term : request.term
								},
								success : function(data) {
									response(data);
								}
							});
						},
						minLength: 0,
						select : function(event, ui) {
							if(parseInt(ui.item.id)!=-2){
								$(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
								event.stopPropagation();
								$(option.formId).bootstrapValidator('revalidateField', option.validateId);
							}
							else{
								$(option.nameField).val('');
								$(option.idField).val('');
								event.stopPropagation();
								 createNewAccountGroup();
							}
							return false;
						},
						 focus: function(event, ui) {
							 event.preventDefault();
							 if(parseInt(ui.item.id)!=-2){
								 $(option.nameField).val(ui.item.name);
									$(option.idField).val(ui.item.id);
								 }
								 else{
									 $(option.nameField).val('');
										$(option.idField).val('');
								 }
								
					        }
					}).data("ui-autocomplete")._renderItem = function(ul, item) {
				return $("<li></li>")
				.data("item.autocomplete", item)
				.append("<a>" +item.name+ "</a>")
				.appendTo(ul);
			};
		}			
	};
	
	$.fn.listCurrency=function(option){
		if(option!=null){
			$(option.nameField).autocomplete(
					{
						source : function(request, response) {
							$.ajax({
								url : option.url,
								async : true,
								dataType : 'json',
								data : {
									q : $(option.nameField).val(),
									term : request.term
								},
								success : function(data) {
									response(data);
								}
							});
						},
						minLength: 0,
						select : function(event, ui) {
							if(parseInt(ui.item.id)!=-2){
								$(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
								event.stopPropagation();
								$(option.formId).bootstrapValidator('revalidateField', option.validateId);
							}
							else{
								$(option.nameField).val('');
								$(option.idField).val('');
								event.stopPropagation();
								 createNewCurrency();
							}
							return false;
						},
						 focus: function(event, ui) {
							 event.preventDefault();
							 if(parseInt(ui.item.id)!=-2){
								 $(option.nameField).val(ui.item.name);
									$(option.idField).val(ui.item.id);
								 }
								 else{
									 $(option.nameField).val('');
										$(option.idField).val('');
								 }
								
					        }
					}).data("ui-autocomplete")._renderItem = function(ul, item) {
				return $("<li></li>")
				.data("item.autocomplete", item)
				.append("<a>" +item.name+ "</a>")
				.appendTo(ul);
			};
		}			
	};
	$.fn.listParentGroup=function(option){
		if(option!=null){
			$(option.nameField).autocomplete(
					{
						source : function(request, response) {
							$.ajax({
								url : option.url,
								async : true,
								dataType : 'json',
								data : {
									q : $(option.nameField).val(),
									term : request.term
								},
								success : function(data) {
									response(data);
								}
							});
						},
						minLength: 0,
						select : function(event, ui) {
							if(parseInt(ui.item.id)!=-2){
								$(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
								$(option.levelField).val(ui.item.level);
								event.stopPropagation();
								$(option.formId).bootstrapValidator('revalidateField', option.validateId);
							}
							else{
								$(option.nameField).val('');
								$(option.idField).val('');
								$(option.levelField).val('');
								event.stopPropagation();
								// createNewAccountGroup();
							}
							return false;
						},
						 focus: function(event, ui) {
							 event.preventDefault();
							 if(parseInt(ui.item.id)!=-2){
								 $(option.nameField).val(ui.item.name);
									$(option.idField).val(ui.item.id);
									$(option.levelField).val(ui.item.level);
								 }
								 else{
									 $(option.nameField).val('');
										$(option.idField).val('');
										$(option.levelField).val('');
								 }
								
					        }
					}).data("ui-autocomplete")._renderItem = function(ul, item) {
				return $("<li></li>")
				.data("item.autocomplete", item)
				.append("<a>" +item.name+ "</a>")
				.appendTo(ul);
			};
		}			
	};
	$.fn.listAccountOfDutiesAndTax=function(option){
		if(option!=null){
			$(option.nameField).autocomplete(
					{
						source : function(request, response) {
							$.ajax({
								url : option.url,
								async : true,
								dataType : 'json',
								data : {
									q : $(option.nameField).val(),
									term : request.term
								},
								success : function(data) {
									response(data);
								}
							});
						},
						minLength: 0,
						select : function(event, ui) {
							if(parseInt(ui.item.id)!=-2){
								$(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
								event.stopPropagation();
								$(option.formId).bootstrapValidator('revalidateField', option.validateId);
							}
							else{
								$(option.nameField).val('');
								$(option.idField).val('');
								event.stopPropagation();
								 createNewAccount();
							}
							return false;
						},
						 focus: function(event, ui) {
							 event.preventDefault();
							 if(parseInt(ui.item.id)!=-2){
								 $(option.nameField).val(ui.item.name);
									$(option.idField).val(ui.item.id);
								 }
								 else{
									 $(option.nameField).val('');
										$(option.idField).val('');
								 }
								
					        }
					}).data("ui-autocomplete")._renderItem = function(ul, item) {
				return $("<li></li>")
				.data("item.autocomplete", item)
				.append("<a>" +item.name+ "</a>")
				.appendTo(ul);
			};
		}			
	};
	$.fn.listAccountOfSales=function(option){
		if(option!=null){
			$(option.nameField).autocomplete(
					{
						source : function(request, response) {
							$.ajax({
								url : option.url,
								async : true,
								dataType : 'json',
								data : {
									q : $(option.nameField).val(),
									term : request.term
								},
								success : function(data) {
									response(data);
								}
							});
						},
						minLength: 0,
						select : function(event, ui) {
							if(parseInt(ui.item.id)!=-2){
								$(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
								event.stopPropagation();
								$(option.formId).bootstrapValidator('revalidateField', option.validateId);
							}
							else{
								$(option.nameField).val('');
								$(option.idField).val('');
								event.stopPropagation();
								 createNewAccount();
							}
							return false;
						},
						 focus: function(event, ui) {
							 event.preventDefault();
							 if(parseInt(ui.item.id)!=-2){
								 $(option.nameField).val(ui.item.name);
									$(option.idField).val(ui.item.id);
								 }
								 else{
									 $(option.nameField).val('');
										$(option.idField).val('');
								 }
								
					        }
					}).data("ui-autocomplete")._renderItem = function(ul, item) {
				return $("<li></li>")
				.data("item.autocomplete", item)
				.append("<a>" +item.name+ "</a>")
				.appendTo(ul);
			};
		}			
	};
	$.fn.listAccountOfPurchase=function(option){
		if(option!=null){
			$(option.nameField).autocomplete(
					{
						source : function(request, response) {
							$.ajax({
								url : option.url,
								async : true,
								dataType : 'json',
								data : {
									q : $(option.nameField).val(),
									term : request.term
								},
								success : function(data) {
									response(data);
								}
							});
						},
						minLength: 0,
						select : function(event, ui) {
							if(parseInt(ui.item.id)!=-2){
								$(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
								event.stopPropagation();
								$(option.formId).bootstrapValidator('revalidateField', option.validateId);
							}
							else{
								$(option.nameField).val('');
								$(option.idField).val('');
								event.stopPropagation();
								 createNewAccount();
							}
							return false;
						},
						 focus: function(event, ui) {
							 event.preventDefault();
							 if(parseInt(ui.item.id)!=-2){
								 $(option.nameField).val(ui.item.name);
									$(option.idField).val(ui.item.id);
								 }
								 else{
									 $(option.nameField).val('');
										$(option.idField).val('');
								 }
								
					        }
					}).data("ui-autocomplete")._renderItem = function(ul, item) {
				return $("<li></li>")
				.data("item.autocomplete", item)
				.append("<a>" +item.name+ "</a>")
				.appendTo(ul);
			};
		}			
	};
	$.fn.listAccountOfExpenditureDirExpense=function(option){
		if(option!=null){
			$(option.nameField).autocomplete(
					{
						source : function(request, response) {
							$.ajax({
								url : option.url,
								async : true,
								dataType : 'json',
								data : {
									q : $(option.nameField).val(),
									term : request.term
								},
								success : function(data) {
									response(data);
								}
							});
						},
						minLength: 0,
						select : function(event, ui) {
							if(parseInt(ui.item.id)!=-2){
								$(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
								event.stopPropagation();
								$(option.formId).bootstrapValidator('revalidateField', option.validateId);
							}
							else{
								$(option.nameField).val('');
								$(option.idField).val('');
								event.stopPropagation();
								 createNewAccount();
							}
							return false;
						},
						 focus: function(event, ui) {
							 event.preventDefault();
							 if(parseInt(ui.item.id)!=-2){
								 $(option.nameField).val(ui.item.name);
									$(option.idField).val(ui.item.id);
								 }
								 else{
									 $(option.nameField).val('');
										$(option.idField).val('');
								 }
								
					        }
					}).data("ui-autocomplete")._renderItem = function(ul, item) {
				return $("<li></li>")
				.data("item.autocomplete", item)
				.append("<a>" +item.name+ "</a>")
				.appendTo(ul);
			};
		}			
	};
	$.fn.listAccountOfExpenseLiability=function(option){
		if(option!=null){
			$(option.nameField).autocomplete(
					{
						source : function(request, response) {
							$.ajax({
								url : option.url,
								async : true,
								dataType : 'json',
								data : {
									q : $(option.nameField).val(),
									term : request.term
								},
								success : function(data) {
									response(data);
								}
							});
						},
						minLength: 0,
						select : function(event, ui) {
							if(parseInt(ui.item.id)!=-2){
								$(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
								$(option.typeField).val(ui.item.type);
								$(option.categoryField).val(ui.item.category);
								event.stopPropagation();
								$(option.formId).bootstrapValidator('revalidateField', option.validateId);
							}
							else{
								$(option.nameField).val('');
								$(option.idField).val('');
								$(option.typeField).val('');
								$(option.categoryField).val('');
								event.stopPropagation();
								 createNewAccount();
							}
							return false;
						},
						 focus: function(event, ui) {
							 event.preventDefault();
							 if(parseInt(ui.item.id)!=-2){
								 	$(option.nameField).val(ui.item.name);
									$(option.idField).val(ui.item.id);
									$(option.typeField).val(ui.item.type);
									$(option.categoryField).val(ui.item.category);
								 }
								 else{
									 	$(option.nameField).val('');
										$(option.idField).val('');
										$(option.typeField).val('');
										$(option.categoryField).val('');
								 }
								
					        }
					}).data("ui-autocomplete")._renderItem = function(ul, item) {
				return $("<li></li>")
				.data("item.autocomplete", item)
				.append("<a>" +item.name+ "</a>")
				.appendTo(ul);
			};
		}			
	};
	$.fn.listCostCenter=function(option){
		if(option!=null){
			$(option.nameField).autocomplete(
					{
						source : function(request, response) {
							$.ajax({
								url : option.url,
								async : true,
								dataType : 'json',
								data : {
									q : $(option.nameField).val(),
									term : request.term
								},
								success : function(data) {
									response(data);
								}
							});
						},
						minLength: 0,
						select : function(event, ui) {
							if(parseInt(ui.item.id)!=-2){
								$(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
								event.stopPropagation();
								$(option.formId).bootstrapValidator('revalidateField', option.validateId);
							}
							else{
								$(option.nameField).val('');
								$(option.idField).val('');
								event.stopPropagation();
								// createNewAccount();
							}
							return false;
						},
						 focus: function(event, ui) {
							 event.preventDefault();
							 if(parseInt(ui.item.id)!=-2){
								 $(option.nameField).val(ui.item.name);
									$(option.idField).val(ui.item.id);
								 }
								 else{
									 $(option.nameField).val('');
										$(option.idField).val('');
								 }
								
					        }
					}).data("ui-autocomplete")._renderItem = function(ul, item) {
				return $("<li></li>")
				.data("item.autocomplete", item)
				.append("<a>" +item.name+ "</a>")
				.appendTo(ul);
			};
		}			
	};
	$.fn.listProfitCenter=function(option){
		if(option!=null){
			$(option.nameField).autocomplete(
					{
						source : function(request, response) {
							$.ajax({
								url : option.url,
								async : true,
								dataType : 'json',
								data : {
									q : $(option.nameField).val(),
									term : request.term
								},
								success : function(data) {
									response(data);
								}
							});
						},
						minLength: 0,
						select : function(event, ui) {
							if(parseInt(ui.item.id)!=-2){
								$(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
								event.stopPropagation();
								$(option.formId).bootstrapValidator('revalidateField', option.validateId);
							}
							else{
								$(option.nameField).val('');
								$(option.idField).val('');
								event.stopPropagation();
								// createNewAccount();
							}
							return false;
						},
						 focus: function(event, ui) {
							 event.preventDefault();
							 if(parseInt(ui.item.id)!=-2){
								 $(option.nameField).val(ui.item.name);
									$(option.idField).val(ui.item.id);
								 }
								 else{
									 $(option.nameField).val('');
										$(option.idField).val('');
								 }
								
					        }
					}).data("ui-autocomplete")._renderItem = function(ul, item) {
				return $("<li></li>")
				.data("item.autocomplete", item)
				.append("<a>" +item.name+ "</a>")
				.appendTo(ul);
			};
		}			
	};
	
	$.fn.listBSAccountGroupType=function(option){
		if(option!=null){
			$(option.nameField).autocomplete(
					{
						source : function(request, response) {
							$.ajax({
								url : option.url,
								async : true,
								dataType : 'json',
								data : {
									q : $(option.nameField).val(),
									term : request.term
								},
								success : function(data) {
									response(data);
								}
							});
						},
						minLength: 0,
						select : function(event, ui) {
							if(parseInt(ui.item.id)!=-2){
								$(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
								event.stopPropagation();
								$(option.formId).bootstrapValidator('revalidateField', option.validateId);
							}
							else{
								$(option.nameField).val('');
								$(option.idField).val('');
								event.stopPropagation();
								// createNewAccount();
							}
							return false;
						},
						 focus: function(event, ui) {
							 event.preventDefault();
							 if(parseInt(ui.item.id)!=-2){
								 $(option.nameField).val(ui.item.name);
									$(option.idField).val(ui.item.id);
								 }
								 else{
									 $(option.nameField).val('');
										$(option.idField).val('');
								 }
								
					        }
					}).data("ui-autocomplete")._renderItem = function(ul, item) {
				return $("<li></li>")
				.data("item.autocomplete", item)
				.append("<a>" +item.name+ "</a>")
				.appendTo(ul);
			};
		}			
	};

	
	$.fn.listPLAccountGroupType=function(option){
		if(option!=null){
			$(option.nameField).autocomplete(
					{
						source : function(request, response) {
							$.ajax({
								url : option.url,
								async : true,
								dataType : 'json',
								data : {
									q : $(option.nameField).val(),
									term : request.term
								},
								success : function(data) {
									response(data);
								}
							});
						},
						minLength: 0,
						select : function(event, ui) {
							if(parseInt(ui.item.id)!=-2){
								$(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
								event.stopPropagation();
								$(option.formId).bootstrapValidator('revalidateField', option.validateId);
							}
							else{
								$(option.nameField).val('');
								$(option.idField).val('');
								event.stopPropagation();
								// createNewAccount();
							}
							return false;
						},
						 focus: function(event, ui) {
							 event.preventDefault();
							 if(parseInt(ui.item.id)!=-2){
								 $(option.nameField).val(ui.item.name);
									$(option.idField).val(ui.item.id);
								 }
								 else{
									 $(option.nameField).val('');
										$(option.idField).val('');
								 }
								
					        }
					}).data("ui-autocomplete")._renderItem = function(ul, item) {
				return $("<li></li>")
				.data("item.autocomplete", item)
				.append("<a>" +item.name+ "</a>")
				.appendTo(ul);
			};
		}			
	};
	
	$.fn.listOperation=function(option){
		if(option!=null){
			$(option.nameField).autocomplete(
					{
						source : function(request, response) {
							$.ajax({
								url : option.url,
								async : true,
								dataType : 'json',
								data : {
									q : $(option.nameField).val(),
									term : request.term
								},
								success : function(data) {
									response(data);
								}
							});
						},
						minLength: 0,
						select : function(event, ui) {
							if(parseInt(ui.item.id)!=-2){
								$(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
								event.stopPropagation();
								$(option.formId).bootstrapValidator('revalidateField', option.validateId);
							}
							else{
								$(option.nameField).val('');
								$(option.idField).val('');
								event.stopPropagation();
								// createNewAccount();
							}
							return false;
						},
						 focus: function(event, ui) {
							 event.preventDefault();
							 if(parseInt(ui.item.id)!=-2){
								 $(option.nameField).val(ui.item.name);
									$(option.idField).val(ui.item.id);
								 }
								 else{
									 $(option.nameField).val('');
										$(option.idField).val('');
								 }
								
					        }
					}).data("ui-autocomplete")._renderItem = function(ul, item) {
				return $("<li></li>")
				.data("item.autocomplete", item)
				.append("<a>" +item.name+ "</a>")
				.appendTo(ul);
			};
		}			
	};
	
	$.fn.listGroupType=function(option){
		if(option!=null){
			$(option.nameField).autocomplete(
					{
						source : function(request, response) {
							$.ajax({
								url : option.url,
								async : true,
								dataType : 'json',
								data : {
									q : $(option.nameField).val(),
									term : request.term
								},
								success : function(data) {
									response(data);
								}
							});
						},
						minLength: 0,
						select : function(event, ui) {
							if(parseInt(ui.item.id)!=-2){
								$(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
								event.stopPropagation();
								$(option.formId).bootstrapValidator('revalidateField', option.validateId);
							}
							else{
								$(option.nameField).val('');
								$(option.idField).val('');
								event.stopPropagation();
								 createNewGroupType();
							}
							return false;
						},
						 focus: function(event, ui) {
							 event.preventDefault();
							 if(parseInt(ui.item.id)!=-2){
								 $(option.nameField).val(ui.item.name);
									$(option.idField).val(ui.item.id);
								 }
								 else{
									 $(option.nameField).val('');
										$(option.idField).val('');
								 }
								
					        }
					}).data("ui-autocomplete")._renderItem = function(ul, item) {
				return $("<li></li>")
				.data("item.autocomplete", item)
				.append("<a>" +item.name+ "</a>")
				.appendTo(ul);
			};
		}			
	};
	$.fn.listParentGroupType=function(option){
		if(option!=null){
			$(option.nameField).autocomplete(
					{
						source : function(request, response) {
							$.ajax({
								url : option.url,
								async : true,
								dataType : 'json',
								data : {
									q : $(option.nameField).val(),
									term : request.term
								},
								success : function(data) {
									response(data);
								}
							});
						},
						minLength: 0,
						select : function(event, ui) {
							if(parseInt(ui.item.id)!=-2){
								$(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
								event.stopPropagation();
								$(option.formId).bootstrapValidator('revalidateField', option.validateId);
							}
							else{
								$(option.nameField).val('');
								$(option.idField).val('');
								event.stopPropagation();
//								 createNewGroupType();
							}
							return false;
						},
						 focus: function(event, ui) {
							 event.preventDefault();
							 if(parseInt(ui.item.id)!=-2){
								 $(option.nameField).val(ui.item.name);
									$(option.idField).val(ui.item.id);
								 }
								 else{
									 $(option.nameField).val('');
										$(option.idField).val('');
								 }
								
					        }
					}).data("ui-autocomplete")._renderItem = function(ul, item) {
				return $("<li></li>")
				.data("item.autocomplete", item)
				.append("<a>" +item.name+ "</a>")
				.appendTo(ul);
			};
		}			
	};	
	
	$.fn.listSizeRange=function(option){
		if(option!=null){
			$(option.nameField).autocomplete(
					{
						source : function(request, response) {
							$.ajax({
								url : option.url,
								async : true,
								dataType : 'json',
								data : {
									q : $(option.nameField).val(),
									term : request.term
								},
								success : function(data) {
									response(data);
								}
							});
						},
						minLength: 0,
						select : function(event, ui) {
							if(parseInt(ui.item.id)!=-2){
								$(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
								event.stopPropagation();
//								getSizeMappingDetailsGrid(ui.item.id);
								$(option.formId).bootstrapValidator('revalidateField', option.validateId);
							}
							else{
								$(option.nameField).val('');
								$(option.idField).val('');
								event.stopPropagation();
								// createNewAccount();
							}
							return false;
						},
						 focus: function(event, ui) {
							 event.preventDefault();
							 if(parseInt(ui.item.id)!=-2){
								 $(option.nameField).val(ui.item.name);
									$(option.idField).val(ui.item.id);
								 }
								 else{
									 $(option.nameField).val('');
										$(option.idField).val('');
								 }
								
					        }
					}).data("ui-autocomplete")._renderItem = function(ul, item) {
				return $("<li></li>")
				.data("item.autocomplete", item)
				.append("<a>" +item.name+ "</a>")
				.appendTo(ul);
			};
		}			
	};	
	
	$.fn.listSize=function(option){
		if(option!=null){
			$(option.nameField).autocomplete(
					{
						source : function(request, response) {
							$.ajax({
								url : option.url,
								async : true,
								dataType : 'json',
								data : {
									q : $(option.nameField).val(),
									term : request.term
								},
								success : function(data) {
									response(data);
								}
							});
						},
						minLength: 0,
						select : function(event, ui) {
							if(parseInt(ui.item.id)!=-2){
								$(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
								event.stopPropagation();
//								getSizeMappingDetailsGrid(ui.item.id);
								$(option.formId).bootstrapValidator('revalidateField', option.validateId);
							}
							else{
								$(option.nameField).val('');
								$(option.idField).val('');
								event.stopPropagation();
								 createNewsize();
							}
							return false;
						},
						 focus: function(event, ui) {
							 event.preventDefault();
							 if(parseInt(ui.item.id)!=-2){
								 $(option.nameField).val(ui.item.name);
									$(option.idField).val(ui.item.id);
								 }
								 else{
									 $(option.nameField).val('');
										$(option.idField).val('');
								 }
								
					        }
					}).data("ui-autocomplete")._renderItem = function(ul, item) {
				return $("<li></li>")
				.data("item.autocomplete", item)
				.append("<a>" +item.name+ "</a>")
				.appendTo(ul);
			};
		}			
	};	
	
	
	$.fn.listSizeRangeSize=function(option){
		if(option!=null){
			$(option.nameField).autocomplete(
					{
						source : function(request, response) {
							$.ajax({
								url : option.url,
								async : true,
								dataType : 'json',
								data : {
									q : $(option.nameField).val(),
									depend_id : $(option.dependIdField).val(),
									term : request.term
								},
								success : function(data) {
									response(data);
								}
							});
						},
						minLength: 0,
						select : function(event, ui) {
							if(parseInt(ui.item.id)!=-2){
								$(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
								event.stopPropagation();
								$(option.formId).bootstrapValidator('revalidateField', option.validateId);
							}
							else{
								$(option.nameField).val('');
								$(option.idField).val('');
								event.stopPropagation();
								// createNewAccount();
							}
							return false;
						},
						 focus: function(event, ui) {
							 event.preventDefault();
							 if(parseInt(ui.item.id)!=-2){
								 $(option.nameField).val(ui.item.name);
									$(option.idField).val(ui.item.id);
								 }
								 else{
									 $(option.nameField).val('');
										$(option.idField).val('');
								 }
								
					        }
					}).data("ui-autocomplete")._renderItem = function(ul, item) {
				return $("<li></li>")
				.data("item.autocomplete", item)
				.append("<a>" +item.name+ "</a>")
				.appendTo(ul);
			};
		}			
	};	
	$.fn.listColor=function(option){
		if(option!=null){
			$(option.nameField).autocomplete(
					{
						source : function(request, response) {
							$.ajax({
								url : option.url,
								async : true,
								dataType : 'json',
								data : {
									q : $(option.nameField).val(),
									term : request.term
								},
								success : function(data) {
									response(data);
								}
							});
						},
						minLength: 0,
						select : function(event, ui) {
							if(parseInt(ui.item.id)!=-2){
								$(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
								event.stopPropagation();
								$(option.formId).bootstrapValidator('revalidateField', option.validateId);
							}
							else{
								$(option.nameField).val('');
								$(option.idField).val('');
								event.stopPropagation();
								// createNewAccount();
							}
							return false;
						},
						 focus: function(event, ui) {
							 event.preventDefault();
							 if(parseInt(ui.item.id)!=-2){
								 $(option.nameField).val(ui.item.name);
									$(option.idField).val(ui.item.id);
								 }
								 else{
									 $(option.nameField).val('');
										$(option.idField).val('');
								 }
								
					        }
					}).data("ui-autocomplete")._renderItem = function(ul, item) {
				return $("<li></li>")
				.data("item.autocomplete", item)
				.append("<a>" +item.name+ "</a>")
				.appendTo(ul);
			};
		}			
	};	
	$.fn.listBOM=function(option){
		if(option!=null){
			$(option.nameField).autocomplete(
					{
						source : function(request, response) {
							$.ajax({
								url : option.url,
								async : true,
								dataType : 'json',
								data : {
									q : $(option.nameField).val(),
									term : request.term
								},
								success : function(data) {
									response(data);
								}
							});
						},
						minLength: 0,
						select : function(event, ui) {
							if(parseInt(ui.item.id)!=-2){
								$(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
								event.stopPropagation();
								$(option.formId).bootstrapValidator('revalidateField', option.validateId);
								
							}
							else{
								$(option.nameField).val('');
								$(option.idField).val('');
								event.stopPropagation();
								createNewGroup();
							}
							return false;
						},
						 focus: function(event, ui) {
							 event.preventDefault();
							 if(parseInt(ui.item.id)!=-2){
								 $(option.nameField).val(ui.item.name);
									$(option.idField).val(ui.item.id);
								 }
								 else{
									 $(option.nameField).val('');
										$(option.idField).val('');
								 }
								
					        },
						change: function (event, ui) {
			                if (!ui.item){
			                	 $(option.nameField).val('');
			                	 $(option.idField).val('');
			                	 $(option.formId).bootstrapValidator('revalidateField', option.validateId);
			                }
			            }
					}).data("ui-autocomplete")._renderItem = function(ul, item) {
				return $("<li></li>")
				.data("item.autocomplete", item)
				.append("<a>" +item.name+ "</a>")
				.appendTo(ul);
			};
		}			
	};
	
	
	$.fn.listAltMaterial=function(option){
		if(option!=null){
			
		$(option.nameField).autocomplete(
		{ 			//position: { my: myPos, at: atPos, of: $(option.nameField),collision: "flip" },
					source : function(request, response) {
						$.ajax({
							url :option.url,
							async : true,
							dataType : 'json',
							data : {
								
								q : $(option.nameField).val(),
								det_mat_id:$("#det_mat_id").val(),
								det_comp_id:$("#det_comp_id").val(),
								term : request.term
							},
							success : function(data) {
								response(data);
							}
						});
					},
					minLength: 0,
					select : function(event, ui) {
						if(parseInt(ui.item.id)!=-2){
							
							$(option.nameField).val(ui.item.name);
							$(option.idField).val(ui.item.id);
							event.stopPropagation();
							 	$(option.formId).bootstrapValidator('revalidateField', option.validateId);
						}
						else{
							$( option.nameField ).val('');
							$(option.idField ).val('');
							event.stopPropagation();
							createNewMaterial();
						}
						return false;
					},
					 focus: function(event, ui) {
						 event.preventDefault();
						 if(parseInt(ui.item.id)!=-2){
							 $(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
							 }
							 else{
								 $(option.nameField).val('');
									$(option.idField).val('');
							 }
							
					 },
					change: function (event, ui) {
		                if (!ui.item){
		                	 $(option.nameField).val('');
		                	 $(option.idField).val('');
		                	 $(option.formId).bootstrapValidator('revalidateField', option.validateId);
		                }
		            }
				}).data("ui-autocomplete")._renderItem = function(ul, item) {
			return $("<li></li>")
			.data("item.autocomplete", item)
			.append("<a>" +item.name+ "</a>")
			.appendTo(ul);
		};
		}
	};
	
	$.fn.listMaterialComponent=function(option){
		if(option!=null){
			
		$(option.nameField).autocomplete(
		{ 			//position: { my: myPos, at: atPos, of: $(option.nameField),collision: "flip" },
					source : function(request, response) {
						$.ajax({
							url :option.url,
							async : true,
							dataType : 'json',
							data : {
								
								q : $(option.nameField).val(),
								tr_tag:$("#tr_tag").val(),
								tr_id:$("#bom_id").val(),
								term : request.term
							},
							success : function(data) {
								response(data);
							}
						});
					},
					minLength: 0,
					select : function(event, ui) {
						if(parseInt(ui.item.id)!=-2){
							
							$(option.nameField).val(ui.item.name);
							$(option.idField).val(ui.item.id);
							event.stopPropagation();
							 	$(option.formId).bootstrapValidator('revalidateField', option.validateId);
						}
						else{
							$( option.nameField ).val('');
							$(option.idField ).val('');
							event.stopPropagation();
							createNewMaterial();
						}
						return false;
					},
					 focus: function(event, ui) {
						 event.preventDefault();
						 if(parseInt(ui.item.id)!=-2){
							 $(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
							 }
							 else{
								 $(option.nameField).val('');
									$(option.idField).val('');
							 }
							
					 },
					change: function (event, ui) {
		                if (!ui.item){
		                	 $(option.nameField).val('');
		                	 $(option.idField).val('');
		                	 $(option.formId).bootstrapValidator('revalidateField', option.validateId);
		                }
		            }
				}).data("ui-autocomplete")._renderItem = function(ul, item) {
			return $("<li></li>")
			.data("item.autocomplete", item)
			.append("<a>" +item.name+ "</a>")
			.appendTo(ul);
		};
		}
	};
	
	
	$.fn.listSizeShedule=function(option){
		if(option!=null){
			$(option.nameField).autocomplete(
					{
						source : function(request, response) {
							$.ajax({
								url : option.url,
								async : true,
								dataType : 'json',
								data : {
									q : $(option.nameField).val(),
									term : request.term
								},
								success : function(data) {
									response(data);
								}
							});
						},
						minLength: 0,
						select : function(event, ui) {
							if(parseInt(ui.item.id)!=-2){
								$(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
								event.stopPropagation();
								$(option.formId).bootstrapValidator('revalidateField', option.validateId);
								
							}
							else{
								$(option.nameField).val('');
								$(option.idField).val('');
								event.stopPropagation();
								createNewGroup();
							}
							return false;
						},
						 focus: function(event, ui) {
							 event.preventDefault();
							 if(parseInt(ui.item.id)!=-2){
								 $(option.nameField).val(ui.item.name);
									$(option.idField).val(ui.item.id);
								 }
								 else{
									 $(option.nameField).val('');
										$(option.idField).val('');
								 }
								
					        }
					}).data("ui-autocomplete")._renderItem = function(ul, item) {
				return $("<li></li>")
				.data("item.autocomplete", item)
				.append("<a>" +item.name+ "</a>")
				.appendTo(ul);
			};
		}			
	};
	$.fn.listAllReports=function(option){
		if(option!=null){
			$(option.nameField).autocomplete(
					{
						source : function(request, response) {
							$.ajax({
								url : option.url,
								async : true,
								dataType : 'json',
								data : {
									q : $(option.nameField).val(),
									term : request.term
								},
								success : function(data) {
									response(data);
								}
							});
						},
						autoFocus: true,
						minLength: 0,
						select : function(event, ui) {
							if(parseInt(ui.item.id)!=-2){
								$(option.nameField).val(ui.item.name);
								$(option.idField).val(ui.item.id);
								$(option.attrField).attr(option.attrName,ui.item.id);
								
								event.stopPropagation();
								$(option.formId).bootstrapValidator('revalidateField', option.validateId);
							}
							else{
								$(option.nameField).val('');
								$(option.idField).val('');
								$(option.attrField).attr(option.attrName,'');
								event.stopPropagation();
								$(option.formId).bootstrapValidator('revalidateField', option.validateId);
								// createNewAccount();
							}
							return false;
						},
						change: function (event, ui) {
			                if (!ui.item){
			                	 $(option.nameField).val('');
			                	 $(option.idField).val('');
			                	 $(option.attrField).attr(option.attrName,'');
			                	 $(option.formId).bootstrapValidator('revalidateField', option.validateId);
			                }
			            }/*,
						 focus: function(event, ui) {
							 event.preventDefault();
							 if(parseInt(ui.item.id)!=-2){
								 $(option.nameField).val(ui.item.name);
									$(option.idField).val(ui.item.id);
								 }
								 else{
									 $(option.nameField).val('');
										$(option.idField).val('');
								 }
								
					        }*/
					}).data("ui-autocomplete")._renderItem = function(ul, item) {
				return $("<li></li>")
				.data("item.autocomplete", item)
				.append("<a>" +item.name+ "</a>")
				.appendTo(ul);
			};
		}			
	};
	
	$.fn.listBOMComp=function(option){
		if(option!=null){
			
		$(option.nameField).autocomplete(
		{ 			//position: { my: myPos, at: atPos, of: $(option.nameField),collision: "flip" },
					source : function(request, response) {
						$.ajax({
							url :option.url,
							async : true,
							dataType : 'json',
							data : {
								
								q : $(option.nameField).val(),
								bom_det_id:$("#bom_det_id").val(),
								term : request.term
							},
							success : function(data) {
								response(data);
							}
						});
					},
					minLength: 0,
					select : function(event, ui) {
						if(parseInt(ui.item.id)!=-2){
							
							$(option.nameField).val(ui.item.name);
							event.stopPropagation();
							 	$(option.formId).bootstrapValidator('revalidateField', option.validateId);
						}
						else{
							$( option.nameField ).val('');
							event.stopPropagation();
							//createNewMaterial();
						}
						return false;
					},
					 focus: function(event, ui) {
						 event.preventDefault();
						 if(parseInt(ui.item.id)!=-2){
							 $(option.nameField).val(ui.item.name);
							 }
							 else{
								 $(option.nameField).val('');
							 }
							
					 }/*,
					change: function (event, ui) {
		                if (!ui.item){
		                	 $(option.nameField).val('');
		                	 $(option.idField).val('');
		                	 $(option.formId).bootstrapValidator('revalidateField', option.validateId);
		                }
		            }*/
				}).data("ui-autocomplete")._renderItem = function(ul, item) {
			return $("<li></li>")
			.data("item.autocomplete", item)
			.append("<a>" +item.name+ "</a>")
			.appendTo(ul);
		};
		}
	};
	
})(window.jQuery);


{
	"patcher" : 	{
		"fileversion" : 1,
		"rect" : [ 674.0, 349.0, 1050.0, 884.0 ],
		"bglocked" : 0,
		"defrect" : [ 674.0, 349.0, 1050.0, 884.0 ],
		"openrect" : [ 0.0, 0.0, 0.0, 0.0 ],
		"openinpresentation" : 1,
		"default_fontsize" : 12.0,
		"default_fontface" : 0,
		"default_fontname" : "Arial",
		"gridonopen" : 0,
		"gridsize" : [ 4.0, 4.0 ],
		"gridsnaponopen" : 0,
		"toolbarvisible" : 1,
		"boxanimatetime" : 200,
		"imprint" : 0,
		"enablehscroll" : 1,
		"enablevscroll" : 1,
		"devicewidth" : 0.0,
		"boxes" : [ 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "bang",
					"fontsize" : 12.0,
					"numinlets" : 2,
					"numoutlets" : 1,
					"patching_rect" : [ 46.0, 79.0, 37.0, 18.0 ],
					"outlettype" : [ "" ],
					"id" : "obj-100",
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "route started",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 2,
					"patching_rect" : [ 46.0, 56.0, 78.0, 20.0 ],
					"outlettype" : [ "", "" ],
					"id" : "obj-99",
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "receive SEVENUP_LIFECYCLE",
					"fontsize" : 12.0,
					"numinlets" : 0,
					"numoutlets" : 1,
					"patching_rect" : [ 45.0, 33.0, 182.0, 20.0 ],
					"outlettype" : [ "" ],
					"id" : "obj-98",
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "!= 1",
					"fontsize" : 12.0,
					"numinlets" : 2,
					"numoutlets" : 1,
					"patching_rect" : [ 207.0, 221.0, 32.5, 20.0 ],
					"outlettype" : [ "int" ],
					"id" : "obj-38",
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "prepend adc",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 1,
					"patching_rect" : [ 208.0, 286.0, 77.0, 20.0 ],
					"outlettype" : [ "" ],
					"id" : "obj-36",
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "== 0",
					"fontsize" : 12.0,
					"numinlets" : 2,
					"numoutlets" : 1,
					"patching_rect" : [ 207.0, 189.0, 35.0, 20.0 ],
					"outlettype" : [ "int" ],
					"id" : "obj-11",
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "Make sure ADC ports are enabled in Monomeserial. \"Calibrate\" starts calibration. In controller mode on the monome, hold down any pattern/slot button in the rightmost navcolumn to send ADC. Tilt monome the full range. Click \"On\" to finish calibration. Sliders should be calibrated to send a full range from 0-127. ",
					"linecount" : 5,
					"presentation_linecount" : 5,
					"fontsize" : 12.0,
					"numinlets" : 1,
					"presentation_rect" : [ 7.0, 50.0, 352.0, 75.0 ],
					"numoutlets" : 0,
					"patching_rect" : [ 111.0, 592.0, 381.0, 75.0 ],
					"presentation" : 1,
					"id" : "obj-9",
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "prepend calibrate",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 1,
					"patching_rect" : [ 93.0, 222.0, 103.0, 20.0 ],
					"outlettype" : [ "" ],
					"id" : "obj-13",
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "== 1",
					"fontsize" : 12.0,
					"numinlets" : 2,
					"numoutlets" : 1,
					"patching_rect" : [ 164.0, 189.0, 35.0, 20.0 ],
					"outlettype" : [ "int" ],
					"id" : "obj-7",
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "outlet",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 165.0, 376.0, 25.0, 25.0 ],
					"id" : "obj-3",
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "+ 1",
					"fontsize" : 12.0,
					"numinlets" : 2,
					"numoutlets" : 1,
					"patching_rect" : [ 693.0, 400.0, 32.5, 20.0 ],
					"outlettype" : [ "int" ],
					"id" : "obj-35",
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "live.slider",
					"hint" : "ADC 7",
					"varname" : "adcvalue.slider8",
					"numinlets" : 1,
					"showname" : 0,
					"presentation_rect" : [ 254.031586, 1.0, 21.0, 51.0 ],
					"numoutlets" : 2,
					"patching_rect" : [ 692.0, 607.0, 39.0, 95.0 ],
					"outlettype" : [ "", "float" ],
					"presentation" : 1,
					"id" : "obj-34",
					"ignoreclick" : 1,
					"parameter_enable" : 1,
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_speedlim" : 0,
							"parameter_steps" : 0,
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 0,
							"parameter_mmax" : 127.0,
							"parameter_mmin" : 0.0,
							"parameter_type" : 0,
							"parameter_initial_enable" : 0,
							"parameter_shortname" : "adcvalue.slider8",
							"parameter_invisible" : 2,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "adcvalue.slider8",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 1,
							"parameter_modmode" : 0,
							"parameter_info" : "",
							"parameter_order" : 0,
							"parameter_units" : ""
						}

					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "live.slider",
					"hint" : "ADC 6",
					"varname" : "adcvalue.slider7",
					"numinlets" : 1,
					"showname" : 0,
					"presentation_rect" : [ 233.031586, 1.0, 21.0, 51.0 ],
					"numoutlets" : 2,
					"patching_rect" : [ 677.0, 592.0, 39.0, 95.0 ],
					"outlettype" : [ "", "float" ],
					"presentation" : 1,
					"id" : "obj-33",
					"ignoreclick" : 1,
					"parameter_enable" : 1,
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_speedlim" : 0,
							"parameter_steps" : 0,
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 0,
							"parameter_mmax" : 127.0,
							"parameter_mmin" : 0.0,
							"parameter_type" : 0,
							"parameter_initial_enable" : 0,
							"parameter_shortname" : "adcvalue.slider7",
							"parameter_invisible" : 2,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "adcvalue.slider7",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 1,
							"parameter_modmode" : 0,
							"parameter_info" : "",
							"parameter_order" : 0,
							"parameter_units" : ""
						}

					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "live.slider",
					"hint" : "ADC 5",
					"varname" : "adcvalue.slider6",
					"numinlets" : 1,
					"showname" : 0,
					"presentation_rect" : [ 212.03157, 1.0, 21.0, 51.0 ],
					"numoutlets" : 2,
					"patching_rect" : [ 662.0, 577.0, 39.0, 95.0 ],
					"outlettype" : [ "", "float" ],
					"presentation" : 1,
					"id" : "obj-32",
					"ignoreclick" : 1,
					"parameter_enable" : 1,
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_speedlim" : 0,
							"parameter_steps" : 0,
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 0,
							"parameter_mmax" : 127.0,
							"parameter_mmin" : 0.0,
							"parameter_type" : 0,
							"parameter_initial_enable" : 0,
							"parameter_shortname" : "adcvalue.slider6",
							"parameter_invisible" : 2,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "adcvalue.slider6",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 1,
							"parameter_modmode" : 0,
							"parameter_info" : "",
							"parameter_order" : 0,
							"parameter_units" : ""
						}

					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "live.slider",
					"hint" : "ADC 4",
					"varname" : "adcvalue.slider5",
					"numinlets" : 1,
					"showname" : 0,
					"presentation_rect" : [ 192.031586, 1.0, 21.0, 51.0 ],
					"numoutlets" : 2,
					"patching_rect" : [ 647.0, 562.0, 39.0, 95.0 ],
					"outlettype" : [ "", "float" ],
					"presentation" : 1,
					"id" : "obj-31",
					"ignoreclick" : 1,
					"parameter_enable" : 1,
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_speedlim" : 0,
							"parameter_steps" : 0,
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 0,
							"parameter_mmax" : 127.0,
							"parameter_mmin" : 0.0,
							"parameter_type" : 0,
							"parameter_initial_enable" : 0,
							"parameter_shortname" : "adcvalue.slider5",
							"parameter_invisible" : 2,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "adcvalue.slider5",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 1,
							"parameter_modmode" : 0,
							"parameter_info" : "",
							"parameter_order" : 0,
							"parameter_units" : ""
						}

					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "live.slider",
					"hint" : "ADC 3",
					"varname" : "adcvalue.slider4",
					"numinlets" : 1,
					"showname" : 0,
					"presentation_rect" : [ 171.031586, 1.0, 21.0, 51.0 ],
					"numoutlets" : 2,
					"patching_rect" : [ 632.0, 547.0, 39.0, 95.0 ],
					"outlettype" : [ "", "float" ],
					"presentation" : 1,
					"id" : "obj-30",
					"ignoreclick" : 1,
					"parameter_enable" : 1,
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_speedlim" : 0,
							"parameter_steps" : 0,
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 0,
							"parameter_mmax" : 127.0,
							"parameter_mmin" : 0.0,
							"parameter_type" : 0,
							"parameter_initial_enable" : 0,
							"parameter_shortname" : "adcvalue.slider4",
							"parameter_invisible" : 2,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "adcvalue.slider4",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 1,
							"parameter_modmode" : 0,
							"parameter_info" : "",
							"parameter_order" : 0,
							"parameter_units" : ""
						}

					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "live.slider",
					"hint" : "ADC 2",
					"varname" : "adcvalue.slider3",
					"numinlets" : 1,
					"showname" : 0,
					"presentation_rect" : [ 151.031586, 1.0, 21.0, 51.0 ],
					"numoutlets" : 2,
					"patching_rect" : [ 617.0, 532.0, 39.0, 95.0 ],
					"outlettype" : [ "", "float" ],
					"presentation" : 1,
					"id" : "obj-29",
					"ignoreclick" : 1,
					"parameter_enable" : 1,
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_speedlim" : 0,
							"parameter_steps" : 0,
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 0,
							"parameter_mmax" : 127.0,
							"parameter_mmin" : 0.0,
							"parameter_type" : 0,
							"parameter_initial_enable" : 0,
							"parameter_shortname" : "adcvalue.slider3",
							"parameter_invisible" : 2,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "adcvalue.slider3",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 1,
							"parameter_modmode" : 0,
							"parameter_info" : "",
							"parameter_order" : 0,
							"parameter_units" : ""
						}

					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "live.slider",
					"hint" : "ADC 1",
					"varname" : "adcvalue.slider2",
					"numinlets" : 1,
					"showname" : 0,
					"presentation_rect" : [ 130.031586, 1.0, 21.0, 51.0 ],
					"numoutlets" : 2,
					"patching_rect" : [ 602.0, 517.0, 39.0, 95.0 ],
					"outlettype" : [ "", "float" ],
					"presentation" : 1,
					"id" : "obj-27",
					"ignoreclick" : 1,
					"parameter_enable" : 1,
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_speedlim" : 0,
							"parameter_steps" : 0,
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 0,
							"parameter_mmax" : 127.0,
							"parameter_mmin" : 0.0,
							"parameter_type" : 0,
							"parameter_initial_enable" : 0,
							"parameter_shortname" : "adcvalue.slider2",
							"parameter_invisible" : 2,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "adcvalue.slider2",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 1,
							"parameter_modmode" : 0,
							"parameter_info" : "",
							"parameter_order" : 0,
							"parameter_units" : ""
						}

					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "t b",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 1,
					"patching_rect" : [ 615.0, 393.0, 24.0, 20.0 ],
					"outlettype" : [ "bang" ],
					"id" : "obj-26",
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "gate 8",
					"fontsize" : 12.0,
					"numinlets" : 2,
					"numoutlets" : 8,
					"patching_rect" : [ 655.0, 457.0, 113.5, 20.0 ],
					"outlettype" : [ "", "", "", "", "", "", "", "" ],
					"id" : "obj-12",
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "int",
					"fontsize" : 12.0,
					"numinlets" : 2,
					"numoutlets" : 1,
					"patching_rect" : [ 625.0, 425.0, 32.5, 20.0 ],
					"outlettype" : [ "int" ],
					"id" : "obj-4",
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "Bank CCs for 8 ADC indexes * 7 Banks",
					"linecount" : 2,
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 776.0, 628.0, 150.0, 34.0 ],
					"id" : "obj-1",
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "% 8",
					"fontsize" : 12.0,
					"numinlets" : 2,
					"numoutlets" : 1,
					"patching_rect" : [ 653.0, 352.0, 32.5, 20.0 ],
					"outlettype" : [ "int" ],
					"id" : "obj-25",
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "- 40",
					"fontsize" : 12.0,
					"numinlets" : 2,
					"numoutlets" : 1,
					"patching_rect" : [ 653.0, 326.0, 32.5, 20.0 ],
					"outlettype" : [ "int" ],
					"id" : "obj-23",
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "unpack i i",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 2,
					"patching_rect" : [ 653.0, 299.0, 61.0, 20.0 ],
					"outlettype" : [ "int", "int" ],
					"id" : "obj-21",
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "88 89 90 91 92 93 94 95",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 777.0, 798.0, 150.0, 20.0 ],
					"id" : "obj-20",
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "80 81 82 83 84 85 86 87",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 777.0, 776.0, 150.0, 20.0 ],
					"id" : "obj-19",
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "72 73 74 75 76 77 78 79",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 777.0, 752.0, 150.0, 20.0 ],
					"id" : "obj-18",
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "64 65 66 67 68 69 70 71",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 777.0, 730.0, 150.0, 20.0 ],
					"id" : "obj-17",
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "56 57 58 59 60 61 62 63",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 777.0, 708.0, 150.0, 20.0 ],
					"id" : "obj-16",
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "48 49 50 51 52 53 54 55",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 776.0, 686.0, 150.0, 20.0 ],
					"id" : "obj-15",
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "40 41 42 43 44 45 46 47",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 776.0, 664.0, 150.0, 20.0 ],
					"id" : "obj-14",
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "Here we listen to the ADC channel for ADC events",
					"linecount" : 2,
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 635.0, 79.0, 170.0, 34.0 ],
					"id" : "obj-10",
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "1",
					"fontsize" : 12.0,
					"numinlets" : 2,
					"numoutlets" : 1,
					"patching_rect" : [ 531.0, 97.0, 32.5, 18.0 ],
					"outlettype" : [ "" ],
					"id" : "obj-8",
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "9",
					"fontsize" : 12.0,
					"numinlets" : 2,
					"numoutlets" : 1,
					"patching_rect" : [ 592.0, 97.0, 32.5, 18.0 ],
					"outlettype" : [ "" ],
					"id" : "obj-5",
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "route NOTE CC",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 3,
					"patching_rect" : [ 615.0, 273.0, 95.0, 20.0 ],
					"outlettype" : [ "", "", "" ],
					"id" : "obj-24",
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "SevenUpMidiDevice",
					"fontsize" : 10.0,
					"numinlets" : 3,
					"numoutlets" : 1,
					"patching_rect" : [ 615.0, 248.0, 106.0, 18.0 ],
					"outlettype" : [ "" ],
					"id" : "obj-22",
					"fontname" : "Arial Bold"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "loadbang",
					"fontsize" : 10.0,
					"numinlets" : 1,
					"numoutlets" : 1,
					"patching_rect" : [ 525.0, 70.0, 55.0, 18.0 ],
					"outlettype" : [ "bang" ],
					"id" : "obj-37",
					"fontname" : "Arial Bold"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "receive SEVENUP_CONTROLLER",
					"fontsize" : 10.0,
					"numinlets" : 0,
					"numoutlets" : 1,
					"patching_rect" : [ 716.0, 187.0, 171.0, 18.0 ],
					"outlettype" : [ "" ],
					"id" : "obj-28",
					"fontname" : "Arial Bold"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "live.slider",
					"hint" : "ADC 0",
					"varname" : "adcvalue.slider1",
					"numinlets" : 1,
					"showname" : 0,
					"presentation_rect" : [ 109.031578, 1.0, 21.0, 51.0 ],
					"numoutlets" : 2,
					"patching_rect" : [ 587.0, 502.0, 39.0, 95.0 ],
					"outlettype" : [ "", "float" ],
					"presentation" : 1,
					"id" : "obj-6",
					"ignoreclick" : 1,
					"parameter_enable" : 1,
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_speedlim" : 0,
							"parameter_steps" : 0,
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 0,
							"parameter_mmax" : 127.0,
							"parameter_mmin" : 0.0,
							"parameter_type" : 0,
							"parameter_initial_enable" : 0,
							"parameter_shortname" : "adcvalue.slider1",
							"parameter_invisible" : 2,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "adcvalue.slider1",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 1,
							"parameter_modmode" : 0,
							"parameter_info" : "",
							"parameter_order" : 0,
							"parameter_units" : ""
						}

					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "live.tab",
					"varname" : "tiltmode.tab",
					"pictures" : [  ],
					"mode" : 1,
					"activebgoncolor" : [ 0.94902, 0.411765, 0.054902, 1.0 ],
					"numinlets" : 1,
					"presentation_rect" : [ 6.0, 4.0, 100.0, 20.0 ],
					"numoutlets" : 3,
					"patching_rect" : [ 135.0, 142.0, 100.0, 20.0 ],
					"outlettype" : [ "", "", "float" ],
					"presentation" : 1,
					"id" : "obj-2",
					"parameter_enable" : 1,
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_speedlim" : 0,
							"parameter_steps" : 0,
							"parameter_enum" : [ "off", "calibrate", "on" ],
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 0,
							"parameter_mmax" : 127.0,
							"parameter_mmin" : 0.0,
							"parameter_initial" : [ 0 ],
							"parameter_type" : 2,
							"parameter_initial_enable" : 1,
							"parameter_shortname" : "live.tab",
							"parameter_invisible" : 0,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "tiltmode.tab",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 1,
							"parameter_modmode" : 0,
							"parameter_info" : "",
							"parameter_order" : 0,
							"parameter_units" : ""
						}

					}

				}

			}
 ],
		"lines" : [ 			{
				"patchline" : 				{
					"source" : [ "obj-100", 0 ],
					"destination" : [ "obj-2", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-98", 0 ],
					"destination" : [ "obj-99", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-99", 0 ],
					"destination" : [ "obj-100", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-36", 0 ],
					"destination" : [ "obj-3", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-38", 0 ],
					"destination" : [ "obj-36", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-11", 0 ],
					"destination" : [ "obj-38", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-2", 0 ],
					"destination" : [ "obj-11", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-13", 0 ],
					"destination" : [ "obj-3", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-7", 0 ],
					"destination" : [ "obj-13", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-2", 0 ],
					"destination" : [ "obj-7", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-23", 0 ],
					"destination" : [ "obj-25", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-21", 0 ],
					"destination" : [ "obj-23", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-24", 1 ],
					"destination" : [ "obj-21", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-28", 0 ],
					"destination" : [ "obj-22", 2 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-8", 0 ],
					"destination" : [ "obj-22", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-5", 0 ],
					"destination" : [ "obj-22", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-22", 0 ],
					"destination" : [ "obj-24", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-37", 0 ],
					"destination" : [ "obj-8", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-37", 0 ],
					"destination" : [ "obj-5", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-25", 0 ],
					"destination" : [ "obj-26", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-26", 0 ],
					"destination" : [ "obj-4", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-4", 0 ],
					"destination" : [ "obj-12", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-21", 1 ],
					"destination" : [ "obj-4", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-12", 0 ],
					"destination" : [ "obj-6", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-12", 1 ],
					"destination" : [ "obj-27", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-12", 2 ],
					"destination" : [ "obj-29", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-12", 3 ],
					"destination" : [ "obj-30", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-12", 4 ],
					"destination" : [ "obj-31", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-12", 5 ],
					"destination" : [ "obj-32", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-12", 6 ],
					"destination" : [ "obj-33", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-12", 7 ],
					"destination" : [ "obj-34", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-25", 0 ],
					"destination" : [ "obj-35", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-35", 0 ],
					"destination" : [ "obj-12", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
 ],
		"parameters" : 		{
			"obj-2" : [ "tiltmode.tab", "live.tab", 0 ],
			"obj-34" : [ "adcvalue.slider8", "adcvalue.slider8", 0 ],
			"obj-6" : [ "adcvalue.slider1", "adcvalue.slider1", 0 ],
			"obj-33" : [ "adcvalue.slider7", "adcvalue.slider7", 0 ],
			"obj-32" : [ "adcvalue.slider6", "adcvalue.slider6", 0 ],
			"obj-31" : [ "adcvalue.slider5", "adcvalue.slider5", 0 ],
			"obj-30" : [ "adcvalue.slider4", "adcvalue.slider4", 0 ],
			"obj-29" : [ "adcvalue.slider3", "adcvalue.slider3", 0 ],
			"obj-27" : [ "adcvalue.slider2", "adcvalue.slider2", 0 ]
		}

	}

}

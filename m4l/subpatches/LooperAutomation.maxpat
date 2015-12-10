{
	"patcher" : 	{
		"fileversion" : 1,
		"rect" : [ 25.0, 69.0, 1272.0, 914.0 ],
		"bglocked" : 0,
		"defrect" : [ 25.0, 69.0, 1272.0, 914.0 ],
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
					"maxclass" : "button",
					"outlettype" : [ "bang" ],
					"id" : "obj-44",
					"patching_rect" : [ 547.0, 100.0, 20.0, 20.0 ],
					"numinlets" : 1,
					"numoutlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "GetNextDeviceDownstreamParamByIndex",
					"outlettype" : [ "" ],
					"id" : "obj-35",
					"fontname" : "Arial",
					"patching_rect" : [ 874.0, 619.0, 237.0, 20.0 ],
					"numinlets" : 1,
					"fontsize" : 12.0,
					"numoutlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "GetNextDeviceDownstreamParamByIndex",
					"outlettype" : [ "" ],
					"id" : "obj-29",
					"fontname" : "Arial",
					"patching_rect" : [ 593.0, 667.0, 237.0, 20.0 ],
					"numinlets" : 1,
					"fontsize" : 12.0,
					"numoutlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "GetNextDeviceDownstreamParamByIndex",
					"outlettype" : [ "" ],
					"id" : "obj-26",
					"fontname" : "Arial",
					"patching_rect" : [ 354.0, 636.0, 237.0, 20.0 ],
					"numinlets" : 1,
					"fontsize" : 12.0,
					"numoutlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "GetNextDeviceDownstreamParamByIndex",
					"outlettype" : [ "" ],
					"id" : "obj-20",
					"fontname" : "Arial",
					"patching_rect" : [ 834.0, 345.0, 237.0, 20.0 ],
					"numinlets" : 1,
					"fontsize" : 12.0,
					"numoutlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "GetNextDeviceDownstreamParamByIndex",
					"outlettype" : [ "" ],
					"id" : "obj-18",
					"fontname" : "Arial",
					"patching_rect" : [ 566.0, 302.0, 237.0, 20.0 ],
					"numinlets" : 1,
					"fontsize" : 12.0,
					"numoutlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "GetNextDeviceDownstreamParamByIndex",
					"outlettype" : [ "" ],
					"id" : "obj-16",
					"fontname" : "Arial",
					"patching_rect" : [ 436.0, 353.0, 237.0, 20.0 ],
					"numinlets" : 1,
					"fontsize" : 12.0,
					"numoutlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "pack",
					"outlettype" : [ "" ],
					"id" : "obj-31",
					"fontname" : "Arial",
					"patching_rect" : [ 50.0, 247.0, 36.0, 20.0 ],
					"numinlets" : 2,
					"fontsize" : 12.0,
					"numoutlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "abs",
					"outlettype" : [ "int" ],
					"id" : "obj-30",
					"fontname" : "Arial",
					"patching_rect" : [ 117.0, 212.0, 30.0, 20.0 ],
					"numinlets" : 1,
					"fontsize" : 12.0,
					"numoutlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "* 0.96",
					"outlettype" : [ "float" ],
					"id" : "obj-10",
					"fontname" : "Arial",
					"patching_rect" : [ 123.0, 174.0, 42.0, 20.0 ],
					"numinlets" : 2,
					"fontsize" : 12.0,
					"numoutlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "zl slice 2",
					"outlettype" : [ "", "" ],
					"id" : "obj-3",
					"fontname" : "Arial",
					"patching_rect" : [ 58.0, 133.0, 57.0, 20.0 ],
					"numinlets" : 2,
					"fontsize" : 12.0,
					"numoutlets" : 2
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "8",
					"outlettype" : [ "" ],
					"id" : "obj-34",
					"fontname" : "Arial",
					"patching_rect" : [ 892.0, 549.0, 32.5, 18.0 ],
					"numinlets" : 2,
					"fontsize" : 12.0,
					"numoutlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "7",
					"outlettype" : [ "" ],
					"id" : "obj-27",
					"fontname" : "Arial",
					"patching_rect" : [ 607.0, 621.0, 32.5, 18.0 ],
					"numinlets" : 2,
					"fontsize" : 12.0,
					"numoutlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "6",
					"outlettype" : [ "" ],
					"id" : "obj-25",
					"fontname" : "Arial",
					"patching_rect" : [ 375.0, 550.0, 32.5, 18.0 ],
					"numinlets" : 2,
					"fontsize" : 12.0,
					"numoutlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "5",
					"outlettype" : [ "" ],
					"id" : "obj-19",
					"fontname" : "Arial",
					"patching_rect" : [ 843.0, 304.0, 32.5, 18.0 ],
					"numinlets" : 2,
					"fontsize" : 12.0,
					"numoutlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "4",
					"outlettype" : [ "" ],
					"id" : "obj-17",
					"fontname" : "Arial",
					"patching_rect" : [ 609.0, 254.0, 32.5, 18.0 ],
					"numinlets" : 2,
					"fontsize" : 12.0,
					"numoutlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "3",
					"outlettype" : [ "" ],
					"id" : "obj-14",
					"fontname" : "Arial",
					"patching_rect" : [ 447.0, 304.0, 32.5, 18.0 ],
					"numinlets" : 2,
					"fontsize" : 12.0,
					"numoutlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "2",
					"outlettype" : [ "" ],
					"id" : "obj-214",
					"fontname" : "Arial",
					"patching_rect" : [ 307.0, 242.0, 32.5, 18.0 ],
					"numinlets" : 2,
					"fontsize" : 12.0,
					"numoutlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "GetNextDeviceDownstreamParamByIndex",
					"outlettype" : [ "" ],
					"id" : "obj-213",
					"fontname" : "Arial",
					"patching_rect" : [ 249.0, 275.0, 237.0, 20.0 ],
					"numinlets" : 1,
					"fontsize" : 12.0,
					"numoutlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"varname" : "M4L.api.DeviceParameterRemote[6]",
					"text" : "M4L.api.DeviceParameterRemote",
					"outlettype" : [ "", "", "" ],
					"id" : "obj-57",
					"fontname" : "Arial Bold",
					"patching_rect" : [ 892.0, 773.0, 166.0, 18.0 ],
					"color" : [ 1.0, 0.0, 0.0, 1.0 ],
					"numinlets" : 3,
					"fontsize" : 10.0,
					"numoutlets" : 3
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "live.dial",
					"varname" : "loop7.dial",
					"presentation_rect" : [ 304.0, 6.0, 44.0, 47.0 ],
					"outlettype" : [ "", "float" ],
					"id" : "obj-58",
					"patching_rect" : [ 922.0, 704.0, 44.0, 47.0 ],
					"presentation" : 1,
					"parameter_enable" : 1,
					"numinlets" : 1,
					"numoutlets" : 2,
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_shortname" : "Loop7",
							"parameter_invisible" : 0,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "start.loop7.dial",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 0,
							"parameter_modmode" : 0,
							"parameter_info" : "",
							"parameter_order" : 0,
							"parameter_units" : "",
							"parameter_speedlim" : 0,
							"parameter_steps" : 0,
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 0,
							"parameter_mmax" : 127.0,
							"parameter_mmin" : 0.0,
							"parameter_type" : 0,
							"parameter_initial_enable" : 0
						}

					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"varname" : "M4L.api.DeviceParameterRemote[5]",
					"text" : "M4L.api.DeviceParameterRemote",
					"outlettype" : [ "", "", "" ],
					"id" : "obj-49",
					"fontname" : "Arial Bold",
					"patching_rect" : [ 689.0, 772.0, 166.0, 18.0 ],
					"color" : [ 1.0, 0.0, 0.0, 1.0 ],
					"numinlets" : 3,
					"fontsize" : 10.0,
					"numoutlets" : 3
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "live.dial",
					"varname" : "loop6.dial",
					"presentation_rect" : [ 259.0, 6.0, 44.0, 47.0 ],
					"outlettype" : [ "", "float" ],
					"id" : "obj-50",
					"patching_rect" : [ 719.0, 703.0, 44.0, 47.0 ],
					"presentation" : 1,
					"parameter_enable" : 1,
					"numinlets" : 1,
					"numoutlets" : 2,
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_shortname" : "Loop6",
							"parameter_invisible" : 0,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "start.loop6.dial",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 0,
							"parameter_modmode" : 0,
							"parameter_info" : "",
							"parameter_order" : 0,
							"parameter_units" : "",
							"parameter_speedlim" : 0,
							"parameter_steps" : 0,
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 0,
							"parameter_mmax" : 127.0,
							"parameter_mmin" : 0.0,
							"parameter_type" : 0,
							"parameter_initial_enable" : 0
						}

					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"varname" : "M4L.api.DeviceParameterRemote[4]",
					"text" : "M4L.api.DeviceParameterRemote",
					"outlettype" : [ "", "", "" ],
					"id" : "obj-41",
					"fontname" : "Arial Bold",
					"patching_rect" : [ 466.0, 773.0, 166.0, 18.0 ],
					"color" : [ 1.0, 0.0, 0.0, 1.0 ],
					"numinlets" : 3,
					"fontsize" : 10.0,
					"numoutlets" : 3
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "live.dial",
					"varname" : "loop5.dial",
					"presentation_rect" : [ 214.0, 6.0, 44.0, 47.0 ],
					"outlettype" : [ "", "float" ],
					"id" : "obj-42",
					"patching_rect" : [ 496.0, 704.0, 44.0, 47.0 ],
					"presentation" : 1,
					"parameter_enable" : 1,
					"numinlets" : 1,
					"numoutlets" : 2,
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_shortname" : "Loop5",
							"parameter_invisible" : 0,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "start.loop5.dial",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 0,
							"parameter_modmode" : 0,
							"parameter_info" : "",
							"parameter_order" : 0,
							"parameter_units" : "",
							"parameter_speedlim" : 0,
							"parameter_steps" : 0,
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 0,
							"parameter_mmax" : 127.0,
							"parameter_mmin" : 0.0,
							"parameter_type" : 0,
							"parameter_initial_enable" : 0
						}

					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"varname" : "M4L.api.DeviceParameterRemote[3]",
					"text" : "M4L.api.DeviceParameterRemote",
					"outlettype" : [ "", "", "" ],
					"id" : "obj-32",
					"fontname" : "Arial Bold",
					"patching_rect" : [ 862.0, 467.0, 166.0, 18.0 ],
					"color" : [ 1.0, 0.0, 0.0, 1.0 ],
					"numinlets" : 3,
					"fontsize" : 10.0,
					"numoutlets" : 3
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "live.dial",
					"varname" : "loop4.dial",
					"presentation_rect" : [ 169.0, 6.0, 44.0, 47.0 ],
					"outlettype" : [ "", "float" ],
					"id" : "obj-33",
					"patching_rect" : [ 892.0, 398.0, 44.0, 47.0 ],
					"presentation" : 1,
					"parameter_enable" : 1,
					"numinlets" : 1,
					"numoutlets" : 2,
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_shortname" : "Loop4",
							"parameter_invisible" : 0,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "start.loop4.dial",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 0,
							"parameter_modmode" : 0,
							"parameter_info" : "",
							"parameter_order" : 0,
							"parameter_units" : "",
							"parameter_speedlim" : 0,
							"parameter_steps" : 0,
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 0,
							"parameter_mmax" : 127.0,
							"parameter_mmin" : 0.0,
							"parameter_type" : 0,
							"parameter_initial_enable" : 0
						}

					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"varname" : "M4L.api.DeviceParameterRemote[2]",
					"text" : "M4L.api.DeviceParameterRemote",
					"outlettype" : [ "", "", "" ],
					"id" : "obj-21",
					"fontname" : "Arial Bold",
					"patching_rect" : [ 674.0, 467.0, 166.0, 18.0 ],
					"color" : [ 1.0, 0.0, 0.0, 1.0 ],
					"numinlets" : 3,
					"fontsize" : 10.0,
					"numoutlets" : 3
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "live.dial",
					"varname" : "loop3.dial",
					"presentation_rect" : [ 124.0, 6.0, 44.0, 47.0 ],
					"outlettype" : [ "", "float" ],
					"id" : "obj-23",
					"patching_rect" : [ 704.0, 398.0, 44.0, 47.0 ],
					"presentation" : 1,
					"parameter_enable" : 1,
					"numinlets" : 1,
					"numoutlets" : 2,
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_shortname" : "Loop3",
							"parameter_invisible" : 0,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "start.loop3.dial",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 0,
							"parameter_modmode" : 0,
							"parameter_info" : "",
							"parameter_order" : 0,
							"parameter_units" : "",
							"parameter_speedlim" : 0,
							"parameter_steps" : 0,
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 0,
							"parameter_mmax" : 127.0,
							"parameter_mmin" : 0.0,
							"parameter_type" : 0,
							"parameter_initial_enable" : 0
						}

					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"varname" : "M4L.api.DeviceParameterRemote[1]",
					"text" : "M4L.api.DeviceParameterRemote",
					"outlettype" : [ "", "", "" ],
					"id" : "obj-5",
					"fontname" : "Arial Bold",
					"patching_rect" : [ 484.0, 469.0, 166.0, 18.0 ],
					"color" : [ 1.0, 0.0, 0.0, 1.0 ],
					"numinlets" : 3,
					"fontsize" : 10.0,
					"numoutlets" : 3
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "live.dial",
					"varname" : "loop2.dial",
					"presentation_rect" : [ 79.0, 6.0, 44.0, 47.0 ],
					"outlettype" : [ "", "float" ],
					"id" : "obj-13",
					"patching_rect" : [ 515.0, 399.0, 44.0, 47.0 ],
					"presentation" : 1,
					"parameter_enable" : 1,
					"numinlets" : 1,
					"numoutlets" : 2,
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_shortname" : "Loop2",
							"parameter_invisible" : 0,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "start.loop2.dial",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 0,
							"parameter_modmode" : 0,
							"parameter_info" : "",
							"parameter_order" : 0,
							"parameter_units" : "",
							"parameter_speedlim" : 0,
							"parameter_steps" : 0,
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 0,
							"parameter_mmax" : 127.0,
							"parameter_mmin" : 0.0,
							"parameter_type" : 0,
							"parameter_initial_enable" : 0
						}

					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "route 96 97 98 99 100 101 102",
					"outlettype" : [ "", "", "", "", "", "", "", "" ],
					"id" : "obj-4",
					"fontname" : "Arial",
					"patching_rect" : [ 65.0, 274.0, 175.0, 20.0 ],
					"numinlets" : 1,
					"fontsize" : 12.0,
					"numoutlets" : 8
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"varname" : "M4L.api.DeviceParameterRemote",
					"text" : "M4L.api.DeviceParameterRemote",
					"outlettype" : [ "", "", "" ],
					"id" : "obj-15",
					"fontname" : "Arial Bold",
					"patching_rect" : [ 295.0, 464.0, 166.0, 18.0 ],
					"color" : [ 1.0, 0.0, 0.0, 1.0 ],
					"numinlets" : 3,
					"fontsize" : 10.0,
					"numoutlets" : 3
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "live.dial",
					"varname" : "loop.dial1",
					"presentation_rect" : [ 34.0, 6.0, 44.0, 47.0 ],
					"outlettype" : [ "", "float" ],
					"id" : "obj-11",
					"patching_rect" : [ 325.0, 395.0, 44.0, 47.0 ],
					"presentation" : 1,
					"parameter_enable" : 1,
					"numinlets" : 1,
					"numoutlets" : 2,
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_shortname" : "Loop1",
							"parameter_invisible" : 0,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "start.loop1.dial",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 0,
							"parameter_modmode" : 0,
							"parameter_info" : "",
							"parameter_order" : 0,
							"parameter_units" : "",
							"parameter_speedlim" : 0,
							"parameter_steps" : 0,
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 0,
							"parameter_mmax" : 127.0,
							"parameter_mmin" : 0.0,
							"parameter_type" : 0,
							"parameter_initial_enable" : 0
						}

					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "inlet",
					"outlettype" : [ "" ],
					"id" : "obj-82",
					"patching_rect" : [ 58.0, 40.0, 25.0, 25.0 ],
					"numinlets" : 0,
					"numoutlets" : 1,
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "inlet",
					"outlettype" : [ "" ],
					"id" : "obj-83",
					"patching_rect" : [ 547.0, 40.0, 25.0, 25.0 ],
					"numinlets" : 0,
					"numoutlets" : 1,
					"comment" : ""
				}

			}
 ],
		"lines" : [ 			{
				"patchline" : 				{
					"source" : [ "obj-34", 0 ],
					"destination" : [ "obj-35", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-35", 0 ],
					"destination" : [ "obj-57", 2 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-27", 0 ],
					"destination" : [ "obj-29", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-29", 0 ],
					"destination" : [ "obj-49", 2 ],
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
					"destination" : [ "obj-41", 2 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-16", 0 ],
					"destination" : [ "obj-5", 2 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-14", 0 ],
					"destination" : [ "obj-16", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-19", 0 ],
					"destination" : [ "obj-20", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-20", 0 ],
					"destination" : [ "obj-32", 2 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-17", 0 ],
					"destination" : [ "obj-18", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-18", 0 ],
					"destination" : [ "obj-21", 2 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-214", 0 ],
					"destination" : [ "obj-213", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-213", 0 ],
					"destination" : [ "obj-15", 2 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-4", 6 ],
					"destination" : [ "obj-58", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-4", 5 ],
					"destination" : [ "obj-50", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-4", 4 ],
					"destination" : [ "obj-42", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-4", 3 ],
					"destination" : [ "obj-33", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-4", 2 ],
					"destination" : [ "obj-23", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-4", 1 ],
					"destination" : [ "obj-13", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-4", 0 ],
					"destination" : [ "obj-11", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-58", 1 ],
					"destination" : [ "obj-57", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-50", 1 ],
					"destination" : [ "obj-49", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-42", 1 ],
					"destination" : [ "obj-41", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-33", 1 ],
					"destination" : [ "obj-32", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-23", 1 ],
					"destination" : [ "obj-21", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-13", 1 ],
					"destination" : [ "obj-5", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-11", 1 ],
					"destination" : [ "obj-15", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-3", 1 ],
					"destination" : [ "obj-10", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-10", 0 ],
					"destination" : [ "obj-30", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-3", 0 ],
					"destination" : [ "obj-31", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-30", 0 ],
					"destination" : [ "obj-31", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-31", 0 ],
					"destination" : [ "obj-4", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-44", 0 ],
					"destination" : [ "obj-214", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-44", 0 ],
					"destination" : [ "obj-14", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-44", 0 ],
					"destination" : [ "obj-17", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-44", 0 ],
					"destination" : [ "obj-19", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-44", 0 ],
					"destination" : [ "obj-25", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-44", 0 ],
					"destination" : [ "obj-27", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-44", 0 ],
					"destination" : [ "obj-34", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-82", 0 ],
					"destination" : [ "obj-3", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-83", 0 ],
					"destination" : [ "obj-44", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
 ],
		"parameters" : 		{
			"obj-41::obj-37::obj-14" : [ "live-path[3]", "live-path", 0 ],
			"obj-23" : [ "start.loop3.dial", "Loop3", 0 ],
			"obj-49::obj-37::obj-14" : [ "live-path[2]", "live-path", 0 ],
			"obj-33" : [ "start.loop4.dial", "Loop4", 0 ],
			"obj-42" : [ "start.loop5.dial", "Loop5", 0 ],
			"obj-50" : [ "start.loop6.dial", "Loop6", 0 ],
			"obj-15::obj-37::obj-14" : [ "live-path[7]", "live-path", 0 ],
			"obj-58" : [ "start.loop7.dial", "Loop7", 0 ],
			"obj-11" : [ "start.loop1.dial", "Loop1", 0 ],
			"obj-5::obj-37::obj-14" : [ "live-path[6]", "live-path", 0 ],
			"obj-57::obj-37::obj-14" : [ "live-path[1]", "live-path", 0 ],
			"obj-21::obj-37::obj-14" : [ "live-path[5]", "live-path", 0 ],
			"obj-32::obj-37::obj-14" : [ "live-path[4]", "live-path", 0 ],
			"obj-13" : [ "start.loop2.dial", "Loop2", 0 ]
		}

	}

}

{
	"patcher" : 	{
		"fileversion" : 1,
		"rect" : [ 25.0, 69.0, 640.0, 480.0 ],
		"bglocked" : 0,
		"defrect" : [ 25.0, 69.0, 640.0, 480.0 ],
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
					"maxclass" : "comment",
					"text" : "Internal is the default and requires no external timing. The internal clock however is a straight clock and does not groove. To apply a groove, choose \"ClockClip\" option and place the SevenUp \"Clock\" midi clip in the same track as SevenUpCore. Change track monitor to \"Off\". Now you can open up the \"Clock\" clip and apply a groove to the midi clip itself which will apply that groove to 7up as a whole. ",
					"linecount" : 17,
					"presentation_linecount" : 6,
					"id" : "obj-8",
					"presentation_rect" : [ 13.0, 30.0, 369.0, 89.0 ],
					"fontname" : "Arial",
					"numinlets" : 1,
					"patching_rect" : [ 88.0, 58.0, 150.0, 241.0 ],
					"presentation" : 1,
					"numoutlets" : 0,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "loadbang",
					"id" : "obj-7",
					"fontname" : "Arial",
					"numinlets" : 1,
					"patching_rect" : [ 21.0, 28.0, 60.0, 20.0 ],
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"outlettype" : [ "bang" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "+ 1",
					"id" : "obj-6",
					"fontname" : "Arial",
					"numinlets" : 2,
					"patching_rect" : [ 41.0, 95.0, 32.5, 20.0 ],
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"outlettype" : [ "int" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "s ---ClockType",
					"id" : "obj-5",
					"fontname" : "Arial",
					"numinlets" : 1,
					"patching_rect" : [ 41.0, 142.0, 89.0, 20.0 ],
					"numoutlets" : 0,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "outlet",
					"id" : "obj-4",
					"numinlets" : 1,
					"patching_rect" : [ 238.0, 189.0, 25.0, 25.0 ],
					"numoutlets" : 0,
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "inlet",
					"id" : "obj-3",
					"numinlets" : 0,
					"patching_rect" : [ 340.0, 94.0, 25.0, 25.0 ],
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "inlet",
					"id" : "obj-2",
					"numinlets" : 0,
					"patching_rect" : [ 240.0, 95.0, 25.0, 25.0 ],
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "live.menu",
					"varname" : "clockmenu",
					"id" : "obj-1",
					"presentation_rect" : [ 14.0, 11.0, 100.0, 15.0 ],
					"numinlets" : 1,
					"patching_rect" : [ 41.0, 68.0, 100.0, 15.0 ],
					"presentation" : 1,
					"pictures" : [  ],
					"parameter_enable" : 1,
					"numoutlets" : 3,
					"outlettype" : [ "", "", "float" ],
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_info" : "",
							"parameter_order" : 0,
							"parameter_units" : "",
							"parameter_speedlim" : 0,
							"parameter_steps" : 0,
							"parameter_enum" : [ "internal", "clockclip" ],
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 10,
							"parameter_mmax" : 127.0,
							"parameter_mmin" : 0.0,
							"parameter_initial" : [ 0.0 ],
							"parameter_type" : 2,
							"parameter_initial_enable" : 1,
							"parameter_shortname" : "live.menu",
							"parameter_invisible" : 0,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "live.menu",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 0,
							"parameter_modmode" : 0
						}

					}

				}

			}
 ],
		"lines" : [ 			{
				"patchline" : 				{
					"source" : [ "obj-7", 0 ],
					"destination" : [ "obj-1", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-6", 0 ],
					"destination" : [ "obj-5", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-1", 0 ],
					"destination" : [ "obj-6", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
 ],
		"parameters" : 		{
			"obj-1" : [ "live.menu", "live.menu", 0 ]
		}

	}

}

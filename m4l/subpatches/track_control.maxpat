{
	"patcher" : 	{
		"fileversion" : 1,
		"rect" : [ 240.0, 44.0, 1035.0, 867.0 ],
		"bglocked" : 0,
		"defrect" : [ 240.0, 44.0, 1035.0, 867.0 ],
		"openrect" : [ 0.0, 0.0, 0.0, 0.0 ],
		"openinpresentation" : 0,
		"default_fontsize" : 12.0,
		"default_fontface" : 0,
		"default_fontname" : "Arial",
		"gridonopen" : 0,
		"gridsize" : [ 15.0, 15.0 ],
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
					"text" : "0",
					"fontsize" : 12.0,
					"patching_rect" : [ 215.0, 118.0, 32.5, 18.0 ],
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-39",
					"outlettype" : [ "" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "route 0",
					"fontsize" : 12.0,
					"patching_rect" : [ 200.0, 96.0, 48.0, 20.0 ],
					"numinlets" : 1,
					"numoutlets" : 2,
					"fontname" : "Arial",
					"id" : "obj-38",
					"outlettype" : [ "", "" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "id 0 is missing track, so we gate those messages. We also clear the track display in case track was just deleted.",
					"linecount" : 5,
					"fontsize" : 12.0,
					"patching_rect" : [ 194.0, 143.0, 156.0, 75.0 ],
					"numinlets" : 1,
					"numoutlets" : 0,
					"fontname" : "Arial",
					"id" : "obj-37"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "gate 1",
					"fontsize" : 12.0,
					"patching_rect" : [ 138.0, 158.0, 44.0, 20.0 ],
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-36",
					"outlettype" : [ "" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "> 0",
					"fontsize" : 12.0,
					"patching_rect" : [ 152.0, 124.0, 32.5, 20.0 ],
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-10",
					"outlettype" : [ "int" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "unpack s i",
					"fontsize" : 12.0,
					"patching_rect" : [ 144.0, 73.0, 65.0, 20.0 ],
					"numinlets" : 1,
					"numoutlets" : 2,
					"fontname" : "Arial",
					"id" : "obj-55",
					"outlettype" : [ "", "int" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "expensive but refreshes entire state",
					"linecount" : 2,
					"fontsize" : 12.0,
					"patching_rect" : [ 223.0, 1.0, 156.0, 34.0 ],
					"numinlets" : 1,
					"numoutlets" : 0,
					"fontname" : "Arial",
					"id" : "obj-35"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "r ---RESETCLIPS",
					"fontsize" : 12.0,
					"patching_rect" : [ 114.0, 6.0, 105.0, 20.0 ],
					"numinlets" : 0,
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-17",
					"outlettype" : [ "" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"patching_rect" : [ 179.0, 295.0, 20.0, 20.0 ],
					"numinlets" : 1,
					"numoutlets" : 1,
					"id" : "obj-13",
					"outlettype" : [ "bang" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "path live_set tracks #1",
					"fontsize" : 12.0,
					"patching_rect" : [ 117.0, 38.0, 130.0, 18.0 ],
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-9",
					"outlettype" : [ "" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "live.thisdevice",
					"fontsize" : 12.0,
					"patching_rect" : [ 25.0, 6.0, 85.0, 20.0 ],
					"numinlets" : 1,
					"numoutlets" : 3,
					"fontname" : "Arial",
					"id" : "obj-76",
					"outlettype" : [ "bang", "int", "int" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "reset, otherwise there may be a saved value and if the track doesn't exist, it won't be refreshed.",
					"linecount" : 4,
					"fontsize" : 12.0,
					"patching_rect" : [ 543.0, 298.0, 156.0, 62.0 ],
					"numinlets" : 1,
					"numoutlets" : 0,
					"fontname" : "Arial",
					"id" : "obj-6"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "0",
					"fontsize" : 12.0,
					"patching_rect" : [ 509.0, 323.0, 32.5, 18.0 ],
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-5",
					"outlettype" : [ "" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "CLIP PRESENT",
					"linecount" : 2,
					"fontsize" : 12.0,
					"patching_rect" : [ 217.0, 618.0, 73.0, 34.0 ],
					"numinlets" : 1,
					"numoutlets" : 0,
					"fontname" : "Arial",
					"id" : "obj-34"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "CUED",
					"fontsize" : 12.0,
					"patching_rect" : [ 140.0, 621.0, 73.0, 20.0 ],
					"numinlets" : 1,
					"numoutlets" : 0,
					"fontname" : "Arial",
					"id" : "obj-33"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "PLAYING",
					"fontsize" : 12.0,
					"patching_rect" : [ 63.0, 620.0, 73.0, 20.0 ],
					"numinlets" : 1,
					"numoutlets" : 0,
					"fontname" : "Arial",
					"id" : "obj-29"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "PAD OFF",
					"fontsize" : 12.0,
					"patching_rect" : [ 301.0, 683.0, 228.0, 20.0 ],
					"numinlets" : 1,
					"numoutlets" : 0,
					"fontname" : "Arial",
					"id" : "obj-28"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "0",
					"fontsize" : 12.0,
					"patching_rect" : [ 288.0, 466.0, 32.5, 18.0 ],
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-21",
					"outlettype" : [ "" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "0",
					"fontsize" : 12.0,
					"patching_rect" : [ 360.0, 582.0, 32.5, 18.0 ],
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-20",
					"outlettype" : [ "" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "0",
					"fontsize" : 12.0,
					"patching_rect" : [ 320.0, 583.0, 32.5, 18.0 ],
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-19",
					"outlettype" : [ "" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "bang",
					"fontsize" : 12.0,
					"patching_rect" : [ 197.0, 440.0, 37.0, 18.0 ],
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-12",
					"outlettype" : [ "" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "0",
					"fontsize" : 12.0,
					"patching_rect" : [ 284.0, 583.0, 32.5, 18.0 ],
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-8",
					"outlettype" : [ "" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "expr ~( $i1 | $i2 | $i3)",
					"fontsize" : 12.0,
					"patching_rect" : [ 299.0, 614.0, 121.0, 20.0 ],
					"numinlets" : 3,
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-7",
					"outlettype" : [ "" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "t l b b",
					"fontsize" : 12.0,
					"patching_rect" : [ 278.0, 378.0, 46.0, 20.0 ],
					"numinlets" : 1,
					"numoutlets" : 3,
					"fontname" : "Arial",
					"id" : "obj-4",
					"outlettype" : [ "", "bang", "bang" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "0",
					"fontsize" : 12.0,
					"patching_rect" : [ 331.0, 468.0, 32.5, 18.0 ],
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-3",
					"outlettype" : [ "" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "0",
					"fontsize" : 12.0,
					"patching_rect" : [ 370.0, 468.0, 32.5, 18.0 ],
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-1",
					"outlettype" : [ "" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "expr ($i2 | $i3) ^ $i1",
					"fontsize" : 12.0,
					"patching_rect" : [ 260.0, 505.0, 115.0, 20.0 ],
					"numinlets" : 3,
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-14",
					"outlettype" : [ "" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "outlet",
					"patching_rect" : [ 299.0, 655.0, 25.0, 25.0 ],
					"numinlets" : 1,
					"numoutlets" : 0,
					"id" : "obj-2",
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "outlet",
					"patching_rect" : [ 212.0, 586.0, 25.0, 25.0 ],
					"numinlets" : 1,
					"numoutlets" : 0,
					"id" : "obj-32",
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "outlet",
					"patching_rect" : [ 138.0, 588.0, 25.0, 25.0 ],
					"numinlets" : 1,
					"numoutlets" : 0,
					"id" : "obj-31",
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "outlet",
					"patching_rect" : [ 60.0, 588.0, 25.0, 25.0 ],
					"numinlets" : 1,
					"numoutlets" : 0,
					"id" : "obj-30",
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "loaded_clips #2",
					"fontsize" : 12.0,
					"patching_rect" : [ 266.0, 353.0, 94.0, 20.0 ],
					"numinlets" : 3,
					"numoutlets" : 2,
					"fontname" : "Arial",
					"id" : "obj-11",
					"outlettype" : [ "", "" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "row_bitmask #2",
					"fontsize" : 10.0,
					"patching_rect" : [ 54.0, 368.0, 86.0, 18.0 ],
					"numinlets" : 1,
					"numoutlets" : 1,
					"fontname" : "Arial Bold",
					"id" : "obj-15",
					"outlettype" : [ "int" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "-1",
					"fontsize" : 12.0,
					"patching_rect" : [ 253.0, 316.0, 32.5, 18.0 ],
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-16",
					"outlettype" : [ "" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "loadbang",
					"fontsize" : 12.0,
					"patching_rect" : [ 253.0, 291.0, 60.0, 20.0 ],
					"numinlets" : 1,
					"numoutlets" : 1,
					"fontname" : "Arial",
					"id" : "obj-18",
					"outlettype" : [ "bang" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "This patch controls the blinking fired clip and the solid playing clip for one track #1 is the track. #2 is the row offset from 0",
					"linecount" : 3,
					"fontsize" : 12.0,
					"patching_rect" : [ 442.0, 618.0, 232.0, 48.0 ],
					"numinlets" : 1,
					"numoutlets" : 0,
					"fontname" : "Arial",
					"id" : "obj-22"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "live.observer fired_slot_index",
					"fontsize" : 10.0,
					"patching_rect" : [ 142.0, 265.0, 152.0, 18.0 ],
					"numinlets" : 2,
					"numoutlets" : 2,
					"fontname" : "Arial Bold",
					"id" : "obj-23",
					"outlettype" : [ "", "" ],
					"saved_object_attributes" : 					{
						"_persistence" : 0
					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "live.observer playing_slot_index",
					"fontsize" : 10.0,
					"patching_rect" : [ 54.0, 243.0, 165.0, 18.0 ],
					"numinlets" : 2,
					"numoutlets" : 2,
					"fontname" : "Arial Bold",
					"id" : "obj-24",
					"outlettype" : [ "", "" ],
					"saved_object_attributes" : 					{
						"_persistence" : 0
					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "live.path",
					"fontsize" : 10.0,
					"patching_rect" : [ 55.0, 73.0, 51.0, 18.0 ],
					"numinlets" : 1,
					"numoutlets" : 3,
					"fontname" : "Arial Bold",
					"id" : "obj-25",
					"outlettype" : [ "", "", "" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "row_bitmask #2",
					"fontsize" : 10.0,
					"patching_rect" : [ 143.0, 368.0, 86.0, 18.0 ],
					"numinlets" : 1,
					"numoutlets" : 1,
					"fontname" : "Arial Bold",
					"id" : "obj-51",
					"outlettype" : [ "int" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "number",
					"hint" : "Fired clip",
					"annotation" : "Fired clip",
					"fontsize" : 10.0,
					"patching_rect" : [ 142.0, 340.0, 37.0, 18.0 ],
					"numinlets" : 1,
					"numoutlets" : 2,
					"fontname" : "Arial Bold",
					"id" : "obj-26",
					"outlettype" : [ "int", "bang" ],
					"minimum" : -2
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "number",
					"hint" : "Playing clip",
					"annotation" : "Playing clip",
					"fontsize" : 10.0,
					"patching_rect" : [ 54.0, 338.0, 32.0, 18.0 ],
					"numinlets" : 1,
					"numoutlets" : 2,
					"fontname" : "Arial Bold",
					"id" : "obj-27",
					"outlettype" : [ "int", "bang" ],
					"minimum" : -1
				}

			}
 ],
		"lines" : [ 			{
				"patchline" : 				{
					"source" : [ "obj-39", 0 ],
					"destination" : [ "obj-4", 0 ],
					"hidden" : 0,
					"midpoints" : [ 224.5, 138.0, 210.0, 138.0, 210.0, 132.0, 186.0, 132.0, 186.0, 228.0, 324.0, 228.0, 324.0, 339.0, 261.0, 339.0, 261.0, 375.0, 287.5, 375.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-38", 0 ],
					"destination" : [ "obj-39", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-55", 1 ],
					"destination" : [ "obj-38", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-17", 0 ],
					"destination" : [ "obj-11", 0 ],
					"hidden" : 0,
					"midpoints" : [ 123.5, 33.0, 114.0, 33.0, 114.0, 66.0, 123.0, 66.0, 123.0, 228.0, 324.0, 228.0, 324.0, 339.0, 275.5, 339.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-13", 0 ],
					"destination" : [ "obj-11", 2 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-13", 0 ],
					"destination" : [ "obj-5", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-24", 0 ],
					"destination" : [ "obj-13", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-23", 0 ],
					"destination" : [ "obj-13", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-11", 1 ],
					"destination" : [ "obj-4", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-11", 0 ],
					"destination" : [ "obj-4", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-9", 0 ],
					"destination" : [ "obj-25", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-76", 0 ],
					"destination" : [ "obj-9", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-16", 0 ],
					"destination" : [ "obj-26", 0 ],
					"hidden" : 0,
					"midpoints" : [ 262.5, 333.0, 151.5, 333.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-16", 0 ],
					"destination" : [ "obj-27", 0 ],
					"hidden" : 0,
					"midpoints" : [ 262.5, 333.0, 63.5, 333.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-18", 0 ],
					"destination" : [ "obj-16", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-24", 0 ],
					"destination" : [ "obj-27", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-23", 0 ],
					"destination" : [ "obj-26", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-4", 1 ],
					"destination" : [ "obj-3", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-4", 2 ],
					"destination" : [ "obj-1", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-4", 0 ],
					"destination" : [ "obj-12", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-12", 0 ],
					"destination" : [ "obj-8", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-14", 0 ],
					"destination" : [ "obj-32", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-4", 0 ],
					"destination" : [ "obj-14", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-1", 0 ],
					"destination" : [ "obj-14", 2 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-3", 0 ],
					"destination" : [ "obj-14", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-1", 0 ],
					"destination" : [ "obj-20", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-3", 0 ],
					"destination" : [ "obj-19", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-12", 0 ],
					"destination" : [ "obj-20", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-12", 0 ],
					"destination" : [ "obj-19", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-19", 0 ],
					"destination" : [ "obj-7", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-20", 0 ],
					"destination" : [ "obj-7", 2 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-7", 0 ],
					"destination" : [ "obj-2", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-8", 0 ],
					"destination" : [ "obj-7", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-21", 0 ],
					"destination" : [ "obj-14", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-21", 0 ],
					"destination" : [ "obj-8", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-4", 0 ],
					"destination" : [ "obj-21", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-12", 0 ],
					"destination" : [ "obj-21", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-15", 0 ],
					"destination" : [ "obj-12", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-15", 0 ],
					"destination" : [ "obj-30", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-15", 0 ],
					"destination" : [ "obj-3", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-27", 0 ],
					"destination" : [ "obj-15", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-51", 0 ],
					"destination" : [ "obj-12", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-51", 0 ],
					"destination" : [ "obj-31", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-51", 0 ],
					"destination" : [ "obj-1", 1 ],
					"hidden" : 0,
					"midpoints" : [ 153.0, 385.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-26", 0 ],
					"destination" : [ "obj-51", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-18", 0 ],
					"destination" : [ "obj-5", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-5", 0 ],
					"destination" : [ "obj-1", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-5", 0 ],
					"destination" : [ "obj-3", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-5", 0 ],
					"destination" : [ "obj-21", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-5", 0 ],
					"destination" : [ "obj-20", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-5", 0 ],
					"destination" : [ "obj-19", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-5", 0 ],
					"destination" : [ "obj-8", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-17", 0 ],
					"destination" : [ "obj-9", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-25", 1 ],
					"destination" : [ "obj-55", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-55", 1 ],
					"destination" : [ "obj-10", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-10", 0 ],
					"destination" : [ "obj-36", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-25", 1 ],
					"destination" : [ "obj-36", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-36", 0 ],
					"destination" : [ "obj-24", 1 ],
					"hidden" : 0,
					"midpoints" : [ 147.5, 228.0, 209.5, 228.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-36", 0 ],
					"destination" : [ "obj-23", 1 ],
					"hidden" : 0,
					"midpoints" : [ 147.5, 228.0, 284.5, 228.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-36", 0 ],
					"destination" : [ "obj-11", 1 ],
					"hidden" : 0,
					"midpoints" : [ 147.5, 228.0, 324.0, 228.0, 324.0, 339.0, 313.0, 339.0 ]
				}

			}
 ]
	}

}

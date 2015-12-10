{
	"patcher" : 	{
		"fileversion" : 1,
		"rect" : [ 998.0, 106.0, 991.0, 1307.0 ],
		"bgcolor" : [ 1.0, 1.0, 1.0, 0.0 ],
		"bglocked" : 0,
		"defrect" : [ 998.0, 106.0, 991.0, 1307.0 ],
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
					"maxclass" : "newobj",
					"text" : "prepend tilt",
					"numinlets" : 1,
					"fontname" : "Arial",
					"numoutlets" : 1,
					"patching_rect" : [ 41.0, 1140.0, 70.0, 20.0 ],
					"id" : "obj-8",
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "prepend looper",
					"numinlets" : 1,
					"fontname" : "Arial",
					"numoutlets" : 1,
					"patching_rect" : [ 2.0, 730.0, 91.0, 20.0 ],
					"id" : "obj-6",
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "route connections looper melodizer1 melodizer2 clock tilt",
					"numinlets" : 1,
					"fontname" : "Arial",
					"numoutlets" : 7,
					"patching_rect" : [ 59.0, 89.0, 312.0, 20.0 ],
					"id" : "obj-21",
					"outlettype" : [ "", "", "", "", "", "", "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "inlet",
					"numinlets" : 0,
					"numoutlets" : 1,
					"patching_rect" : [ 44.0, 35.0, 25.0, 25.0 ],
					"id" : "obj-22",
					"outlettype" : [ "" ],
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "bpatcher",
					"args" : [  ],
					"presentation_rect" : [ 59.0, 630.0, 384.0, 127.0 ],
					"numinlets" : 2,
					"numoutlets" : 1,
					"patching_rect" : [ 65.0, 730.0, 340.0, 130.0 ],
					"id" : "obj-19",
					"presentation" : 1,
					"outlettype" : [ "" ],
					"name" : "SevenUpMelodizer2Panel.maxpat"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "prepend melodizer2",
					"numinlets" : 1,
					"fontname" : "Arial",
					"numoutlets" : 1,
					"patching_rect" : [ 5.0, 879.0, 117.0, 20.0 ],
					"id" : "obj-18",
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "prepend melodizer1",
					"numinlets" : 1,
					"fontname" : "Arial",
					"numoutlets" : 1,
					"patching_rect" : [ 3.0, 805.0, 117.0, 20.0 ],
					"id" : "obj-17",
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "bpatcher",
					"args" : [  ],
					"presentation_rect" : [ 59.0, 892.0, 384.0, 127.0 ],
					"numinlets" : 0,
					"numoutlets" : 1,
					"patching_rect" : [ 65.0, 1003.0, 340.0, 130.0 ],
					"id" : "obj-15",
					"presentation" : 1,
					"outlettype" : [ "" ],
					"name" : "SevenUpTiltSettings.maxpat"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "bpatcher",
					"args" : [  ],
					"presentation_rect" : [ 59.0, 761.0, 384.0, 127.0 ],
					"numinlets" : 2,
					"numoutlets" : 1,
					"patching_rect" : [ 64.0, 864.0, 340.0, 130.0 ],
					"id" : "obj-14",
					"presentation" : 1,
					"outlettype" : [ "" ],
					"name" : "SevenUpClockSettings.maxpat"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "bpatcher",
					"args" : [  ],
					"presentation_rect" : [ 59.0, 369.0, 384.0, 127.0 ],
					"numinlets" : 2,
					"numoutlets" : 1,
					"patching_rect" : [ 65.0, 458.0, 340.0, 130.0 ],
					"id" : "obj-13",
					"presentation" : 1,
					"outlettype" : [ "" ],
					"name" : "SevenUpLooperPanel.maxpat"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "to UI",
					"presentation_rect" : [ 258.0, 1291.0, 150.0, 20.0 ],
					"numinlets" : 1,
					"fontname" : "Arial",
					"numoutlets" : 0,
					"patching_rect" : [ 450.0, 1150.0, 150.0, 20.0 ],
					"id" : "obj-12",
					"presentation" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "to SevenUpCore",
					"presentation_rect" : [ 57.0, 1290.0, 150.0, 20.0 ],
					"numinlets" : 1,
					"fontname" : "Arial",
					"numoutlets" : 0,
					"patching_rect" : [ 10.0, 1226.0, 150.0, 20.0 ],
					"id" : "obj-11",
					"presentation" : 1,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "outlet",
					"presentation_rect" : [ 252.0, 1250.0, 25.0, 25.0 ],
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 447.0, 1113.0, 25.0, 25.0 ],
					"id" : "obj-10",
					"presentation" : 1,
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "route connections looper melodizer1 melodizer2 clock tilt",
					"numinlets" : 1,
					"fontname" : "Arial",
					"numoutlets" : 7,
					"patching_rect" : [ 433.0, 90.0, 312.0, 20.0 ],
					"id" : "obj-9",
					"outlettype" : [ "", "", "", "", "", "", "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "outlet",
					"hint" : "Raw messages to SevenUp4LiveCore",
					"presentation_rect" : [ 64.0, 1250.0, 25.0, 25.0 ],
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 21.0, 1191.0, 25.0, 25.0 ],
					"id" : "obj-7",
					"presentation" : 1,
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "bpatcher",
					"args" : [  ],
					"presentation_rect" : [ 59.0, 500.0, 384.0, 127.0 ],
					"numinlets" : 2,
					"numoutlets" : 1,
					"patching_rect" : [ 65.0, 593.0, 340.0, 130.0 ],
					"id" : "obj-5",
					"presentation" : 1,
					"outlettype" : [ "" ],
					"name" : "SevenUpMelodizerPanel.maxpat"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "bpatcher",
					"varname" : "SevenUpPatchSettingsPanel",
					"args" : [  ],
					"presentation_rect" : [ 59.0, 237.0, 384.0, 127.0 ],
					"numinlets" : 0,
					"numoutlets" : 1,
					"patching_rect" : [ 65.0, 322.0, 340.0, 130.0 ],
					"id" : "obj-2",
					"presentation" : 1,
					"outlettype" : [ "" ],
					"name" : "SevenUpPatchSettingsPanel.maxpat"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "thispatcher",
					"numinlets" : 1,
					"fontname" : "Arial",
					"numoutlets" : 2,
					"patching_rect" : [ 641.0, 199.0, 69.0, 20.0 ],
					"id" : "obj-4",
					"outlettype" : [ "", "" ],
					"fontsize" : 12.0,
					"save" : [ "#N", "thispatcher", ";", "#Q", "end", ";" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "inlet",
					"numinlets" : 0,
					"numoutlets" : 1,
					"patching_rect" : [ 418.0, 36.0, 25.0, 25.0 ],
					"id" : "obj-3",
					"outlettype" : [ "" ],
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "bpatcher",
					"varname" : "SevenUpConnectionSettingsPanel",
					"args" : [  ],
					"presentation_rect" : [ 57.0, 104.0, 386.0, 127.0 ],
					"numinlets" : 2,
					"numoutlets" : 2,
					"patching_rect" : [ 65.0, 185.0, 340.0, 130.0 ],
					"id" : "obj-1",
					"presentation" : 1,
					"outlettype" : [ "", "" ],
					"name" : "SevenUpAutoConnectionPanel.maxpat"
				}

			}
 ],
		"lines" : [ 			{
				"patchline" : 				{
					"source" : [ "obj-9", 0 ],
					"destination" : [ "obj-1", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-21", 0 ],
					"destination" : [ "obj-1", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-1", 1 ],
					"destination" : [ "obj-10", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-1", 0 ],
					"destination" : [ "obj-7", 0 ],
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
					"source" : [ "obj-15", 0 ],
					"destination" : [ "obj-8", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-19", 0 ],
					"destination" : [ "obj-18", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-21", 4 ],
					"destination" : [ "obj-14", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-9", 4 ],
					"destination" : [ "obj-14", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-9", 3 ],
					"destination" : [ "obj-19", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-9", 2 ],
					"destination" : [ "obj-5", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-9", 1 ],
					"destination" : [ "obj-13", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-3", 0 ],
					"destination" : [ "obj-9", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-9", 6 ],
					"destination" : [ "obj-4", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-21", 3 ],
					"destination" : [ "obj-19", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-21", 2 ],
					"destination" : [ "obj-5", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-21", 1 ],
					"destination" : [ "obj-13", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-22", 0 ],
					"destination" : [ "obj-21", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-21", 6 ],
					"destination" : [ "obj-4", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-18", 0 ],
					"destination" : [ "obj-7", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-17", 0 ],
					"destination" : [ "obj-7", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-6", 0 ],
					"destination" : [ "obj-7", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-13", 0 ],
					"destination" : [ "obj-6", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-5", 0 ],
					"destination" : [ "obj-17", 0 ],
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
 ],
		"parameters" : 		{
			"obj-19::obj-6" : [ "sevenup-transpose-ctrl", "live.menu", 0 ],
			"obj-15::obj-29" : [ "adcvalue.slider3", "adcvalue.slider3", 0 ],
			"obj-1::obj-37" : [ "live.grid", "live.grid", 0 ],
			"obj-19::obj-9" : [ "sevenup-scale-name", "live.menu", 0 ],
			"obj-13::obj-3" : [ "live.menu[30]", "live.menu", 0 ],
			"obj-19::obj-33" : [ "sevenup-mgroup-slot6", "live.menu", 0 ],
			"obj-13::obj-19" : [ "live.menu[34]", "live.menu", 0 ],
			"obj-13::obj-37" : [ "live.menu[27]", "live.menu", 0 ],
			"obj-13::obj-84" : [ "live.menu[5]", "live.menu", 0 ],
			"obj-5::obj-21" : [ "sevenup-mmode-slot6[1]", "live.menu", 0 ],
			"obj-1::obj-14" : [ "sevenup-osc-listen-port", "live.numbox", 0 ],
			"obj-5::obj-40" : [ "sevenup-mgroup-slot3[1]", "live.menu", 0 ],
			"obj-5::obj-32" : [ "sevenup-mgroup-slot5[1]", "live.menu", 0 ],
			"obj-19::obj-36" : [ "sevenup-mgroup-slot2", "live.menu", 0 ],
			"obj-19::obj-32" : [ "sevenup-mgroup-slot5", "live.menu", 0 ],
			"obj-13::obj-18" : [ "live.menu[21]", "live.menu", 0 ],
			"obj-19::obj-19" : [ "sevenup-mmode-slot1", "live.menu", 0 ],
			"obj-13::obj-33" : [ "live.menu[36]", "live.menu", 0 ],
			"obj-19::obj-22" : [ "sevenup-mmode-slot5", "live.menu", 0 ],
			"obj-13::obj-13" : [ "live.menu[26]", "live.menu", 0 ],
			"obj-13::obj-2" : [ "live.menu[35]", "live.menu", 0 ],
			"obj-13::obj-11" : [ "live.menu[25]", "live.menu", 0 ],
			"obj-15::obj-27" : [ "adcvalue.slider2", "adcvalue.slider2", 0 ],
			"obj-5::obj-19" : [ "sevenup-mmode-slot1[1]", "live.menu", 0 ],
			"obj-15::obj-6" : [ "adcvalue.slider1", "adcvalue.slider1", 0 ],
			"obj-5::obj-13" : [ "sevenup-mmode-slot3[1]", "live.menu", 0 ],
			"obj-1::obj-13" : [ "sevenup-osc-host-port", "live.numbox", 0 ],
			"obj-5::obj-22" : [ "sevenup-mmode-slot5[1]", "live.menu", 0 ],
			"obj-5::obj-5" : [ "sevenup-melodizer-tool-mode[1]", "live.menu", 0 ],
			"obj-2::obj-7" : [ "live.button[3]", "live.button", 0 ],
			"obj-19::obj-21" : [ "sevenup-mmode-slot6", "live.menu", 0 ],
			"obj-19::obj-3" : [ "sevenup-mmode-slot4", "live.menu", 0 ],
			"obj-14::obj-1" : [ "live.menu", "live.menu", 0 ],
			"obj-13::obj-83" : [ "live.menu[4]", "live.menu", 0 ],
			"obj-19::obj-11" : [ "sevenup-mgroup-slot1", "live.menu", 0 ],
			"obj-13::obj-9" : [ "live.menu[22]", "live.menu", 0 ],
			"obj-13::obj-6" : [ "live.text[1]", "live.menu", 0 ],
			"obj-13::obj-77" : [ "live.menu[2]", "live.menu", 0 ],
			"obj-5::obj-9" : [ "sevenup-scale-name[1]", "live.menu", 0 ],
			"obj-5::obj-2" : [ "sevenup-mgroup-slot4[1]", "live.menu", 0 ],
			"obj-5::obj-3" : [ "sevenup-mmode-slot4[1]", "live.menu", 0 ],
			"obj-1::obj-4" : [ "live.toggle", "live.toggle", 0 ],
			"obj-5::obj-37" : [ "sevenup-mgroup-slot7[1]", "live.menu", 0 ],
			"obj-19::obj-20" : [ "sevenup-mmode-slot7", "live.menu", 0 ],
			"obj-2::obj-16" : [ "live.button[2]", "live.button", 0 ],
			"obj-19::obj-37" : [ "sevenup-mgroup-slot7", "live.menu", 0 ],
			"obj-2::obj-23" : [ "sevenup-patch-path[1]", "sevenup-patch-path[1]", 0 ],
			"obj-19::obj-13" : [ "sevenup-mmode-slot3", "live.menu", 0 ],
			"obj-13::obj-85" : [ "live.menu[6]", "live.menu", 0 ],
			"obj-19::obj-5" : [ "sevenup-melodizer-tool-mode", "live.menu", 0 ],
			"obj-13::obj-40" : [ "live.menu[23]", "live.menu", 0 ],
			"obj-13::obj-36" : [ "live.menu[32]", "live.menu", 0 ],
			"obj-1::obj-74" : [ "sevenup-osc-autoconf-listen-port", "live.numbox", 0 ],
			"obj-13::obj-20" : [ "live.menu[31]", "live.menu", 0 ],
			"obj-15::obj-34" : [ "adcvalue.slider8", "adcvalue.slider8", 0 ],
			"obj-5::obj-8" : [ "sevenup-record-mode[1]", "live.menu", 0 ],
			"obj-5::obj-11" : [ "sevenup-mgroup-slot1[1]", "live.menu", 0 ],
			"obj-1::obj-52" : [ "sevenup-address-ctrl[1]", "sevenup-prefix-ctrl[2]", 0 ],
			"obj-5::obj-6" : [ "sevenup-transpose-ctrl[1]", "live.menu", 0 ],
			"obj-1::obj-51" : [ "sevenup-prefix-ctrl[1]", "sevenup-prefix-ctrl[1]", 0 ],
			"obj-1::obj-70" : [ "live.text", "live.text", 0 ],
			"obj-1::obj-65::obj-275" : [ "monome.unit[1]", "monome.unit", 0 ],
			"obj-15::obj-2" : [ "tiltmode.tab", "live.tab", 0 ],
			"obj-19::obj-8" : [ "sevenup-record-mode", "live.menu", 0 ],
			"obj-13::obj-8" : [ "live.menu[20]", "live.menu", 0 ],
			"obj-19::obj-2" : [ "sevenup-mgroup-slot4", "live.menu", 0 ],
			"obj-13::obj-21" : [ "live.menu[33]", "live.menu", 0 ],
			"obj-19::obj-40" : [ "sevenup-mgroup-slot3", "live.menu", 0 ],
			"obj-13::obj-53" : [ "live.menu[1]", "live.menu", 0 ],
			"obj-13::obj-32" : [ "live.menu[28]", "live.menu", 0 ],
			"obj-13::obj-22" : [ "live.menu[24]", "live.menu", 0 ],
			"obj-2::obj-2" : [ "live.drop[1]", "live.drop", 0 ],
			"obj-5::obj-20" : [ "sevenup-mmode-slot7[1]", "live.menu", 0 ],
			"obj-1::obj-15" : [ "sevenup-monome-ctrl", "live.menu", 0 ],
			"obj-5::obj-33" : [ "sevenup-mgroup-slot6[1]", "live.menu", 0 ],
			"obj-15::obj-33" : [ "adcvalue.slider7", "adcvalue.slider7", 0 ],
			"obj-5::obj-18" : [ "sevenup-mmode-slot2[1]", "live.menu", 0 ],
			"obj-15::obj-32" : [ "adcvalue.slider6", "adcvalue.slider6", 0 ],
			"obj-5::obj-36" : [ "sevenup-mgroup-slot2[1]", "live.menu", 0 ],
			"obj-15::obj-31" : [ "adcvalue.slider5", "adcvalue.slider5", 0 ],
			"obj-19::obj-18" : [ "sevenup-mmode-slot2", "live.menu", 0 ],
			"obj-15::obj-30" : [ "adcvalue.slider4", "adcvalue.slider4", 0 ]
		}

	}

}

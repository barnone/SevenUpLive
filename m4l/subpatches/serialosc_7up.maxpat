{
	"patcher" : 	{
		"fileversion" : 1,
		"rect" : [ 31.0, 44.0, 2027.0, 1156.0 ],
		"bglocked" : 0,
		"defrect" : [ 31.0, 44.0, 2027.0, 1156.0 ],
		"openrect" : [ 0.0, 0.0, 0.0, 0.0 ],
		"openinpresentation" : 1,
		"default_fontsize" : 12.0,
		"default_fontface" : 0,
		"default_fontname" : "Andale Mono",
		"gridonopen" : 0,
		"gridsize" : [ 8.0, 8.0 ],
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
					"text" : "prepend port",
					"fontname" : "Andale Mono",
					"numinlets" : 1,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-70",
					"outlettype" : [ "" ],
					"patching_rect" : [ 653.0, 198.0, 97.0, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "+ 1",
					"fontname" : "Andale Mono",
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-52",
					"outlettype" : [ "int" ],
					"patching_rect" : [ 653.0, 175.0, 32.5, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "21386",
					"fontname" : "Andale Mono",
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-65",
					"outlettype" : [ "" ],
					"patching_rect" : [ 875.0, 935.0, 50.0, 18.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"id" : "obj-51",
					"outlettype" : [ "bang" ],
					"patching_rect" : [ 966.0, 829.0, 20.0, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "pak #1 5555",
					"fontname" : "Andale Mono",
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-21",
					"outlettype" : [ "" ],
					"patching_rect" : [ 859.0, 1005.0, 90.0, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "/sys/port $1",
					"fontname" : "Andale Mono",
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-20",
					"outlettype" : [ "" ],
					"patching_rect" : [ 348.0, 610.0, 97.0, 18.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "t i",
					"fontname" : "Andale Mono",
					"numinlets" : 1,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-7",
					"outlettype" : [ "int" ],
					"patching_rect" : [ 906.0, 969.0, 32.0, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "+ 10000",
					"fontname" : "Andale Mono",
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-8",
					"outlettype" : [ "int" ],
					"patching_rect" : [ 905.0, 899.0, 61.0, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "random 30000",
					"fontname" : "Andale Mono",
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-15",
					"outlettype" : [ "int" ],
					"patching_rect" : [ 888.0, 860.0, 97.0, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "loadbang",
					"fontname" : "Andale Mono",
					"numinlets" : 1,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-17",
					"outlettype" : [ "bang" ],
					"patching_rect" : [ 892.0, 831.0, 68.0, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"fontname" : "Andale Mono",
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-4",
					"outlettype" : [ "" ],
					"patching_rect" : [ 188.0, 903.0, 50.0, 18.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"fontname" : "Andale Mono",
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-3",
					"outlettype" : [ "" ],
					"patching_rect" : [ 227.0, 879.0, 50.0, 18.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"fontname" : "Andale Mono",
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-69",
					"outlettype" : [ "" ],
					"patching_rect" : [ 267.0, 903.0, 50.0, 18.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"id" : "obj-68",
					"outlettype" : [ "bang" ],
					"patching_rect" : [ 238.0, 416.0, 20.0, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "0",
					"fontname" : "Andale Mono",
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-67",
					"outlettype" : [ "" ],
					"patching_rect" : [ 732.0, 150.0, 32.5, 18.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "1",
					"fontname" : "Andale Mono",
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-66",
					"outlettype" : [ "" ],
					"patching_rect" : [ 584.0, 147.0, 32.5, 18.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "gate 1",
					"fontname" : "Andale Mono",
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-64",
					"outlettype" : [ "" ],
					"patching_rect" : [ 580.0, 196.0, 54.0, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "port 7000",
					"fontname" : "Andale Mono",
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-62",
					"outlettype" : [ "" ],
					"patching_rect" : [ 652.0, 149.0, 75.0, 18.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "t b",
					"fontname" : "Andale Mono",
					"numinlets" : 1,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-60",
					"outlettype" : [ "bang" ],
					"patching_rect" : [ 706.0, 114.0, 32.0, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "t b",
					"fontname" : "Andale Mono",
					"numinlets" : 1,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-59",
					"outlettype" : [ "bang" ],
					"patching_rect" : [ 674.0, 114.0, 32.0, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "route 0 1",
					"fontname" : "Andale Mono",
					"numinlets" : 1,
					"numoutlets" : 3,
					"fontsize" : 12.0,
					"id" : "obj-58",
					"outlettype" : [ "", "", "" ],
					"patching_rect" : [ 678.0, 91.0, 75.0, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"fontname" : "Andale Mono",
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-57",
					"outlettype" : [ "" ],
					"patching_rect" : [ 838.0, 151.0, 50.0, 18.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "Sending 1 makes MAX release inbound port so other apps can use it.\nSending 0 makes MAX regain the inbound port",
					"linecount" : 2,
					"fontname" : "Andale Mono",
					"numinlets" : 1,
					"numoutlets" : 0,
					"fontsize" : 12.0,
					"id" : "obj-56",
					"patching_rect" : [ 555.0, 52.0, 532.0, 33.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "inlet",
					"numinlets" : 0,
					"numoutlets" : 1,
					"id" : "obj-53",
					"outlettype" : [ "" ],
					"patching_rect" : [ 677.0, 3.0, 25.0, 25.0 ],
					"comment" : "Sending 1 makes MAX release inbound port so other apps can use it. Sending 0 makes MAX regain the inbound port"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "set",
					"fontname" : "Andale Mono",
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-48",
					"outlettype" : [ "" ],
					"patching_rect" : [ 549.0, 855.0, 32.5, 18.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "set $1",
					"fontname" : "Andale Mono",
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-45",
					"outlettype" : [ "" ],
					"patching_rect" : [ 647.0, 855.0, 54.0, 18.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "set $1",
					"fontname" : "Andale Mono",
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-43",
					"outlettype" : [ "" ],
					"patching_rect" : [ 584.0, 855.0, 54.0, 18.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "outlet",
					"numinlets" : 1,
					"numoutlets" : 0,
					"id" : "obj-42",
					"patching_rect" : [ 1077.0, 532.0, 25.0, 25.0 ],
					"comment" : "Rotation in degrees"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "outlet",
					"numinlets" : 1,
					"numoutlets" : 0,
					"id" : "obj-41",
					"patching_rect" : [ 1026.0, 532.0, 25.0, 25.0 ],
					"comment" : "Prefix"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "outlet",
					"numinlets" : 1,
					"numoutlets" : 0,
					"id" : "obj-40",
					"patching_rect" : [ 967.0, 532.0, 25.0, 25.0 ],
					"comment" : "/sys/port"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "outlet",
					"numinlets" : 1,
					"numoutlets" : 0,
					"id" : "obj-39",
					"patching_rect" : [ 912.0, 532.0, 25.0, 25.0 ],
					"comment" : "host address"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "outlet",
					"numinlets" : 1,
					"numoutlets" : 0,
					"id" : "obj-37",
					"patching_rect" : [ 860.0, 532.0, 25.0, 25.0 ],
					"comment" : "Grid size pair"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "outlet",
					"numinlets" : 1,
					"numoutlets" : 0,
					"id" : "obj-36",
					"patching_rect" : [ 814.0, 532.0, 25.0, 25.0 ],
					"comment" : "Device ID"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "t b",
					"fontname" : "Andale Mono",
					"numinlets" : 1,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-35",
					"outlettype" : [ "bang" ],
					"patching_rect" : [ 1268.0, 431.0, 32.0, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "set",
					"fontname" : "Andale Mono",
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-34",
					"outlettype" : [ "" ],
					"patching_rect" : [ 640.0, 306.0, 32.5, 18.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "0",
					"fontname" : "Andale Mono",
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-32",
					"outlettype" : [ "" ],
					"patching_rect" : [ 1084.0, 343.0, 50.0, 18.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "/example",
					"linecount" : 2,
					"fontname" : "Andale Mono",
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-31",
					"outlettype" : [ "" ],
					"patching_rect" : [ 1007.0, 348.0, 50.0, 31.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "10215",
					"fontname" : "Andale Mono",
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-30",
					"outlettype" : [ "" ],
					"patching_rect" : [ 940.0, 348.0, 50.0, 18.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "127.0.0.1",
					"linecount" : 2,
					"fontname" : "Andale Mono",
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-28",
					"outlettype" : [ "" ],
					"patching_rect" : [ 872.0, 337.0, 50.0, 31.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "16 8",
					"fontname" : "Andale Mono",
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-27",
					"outlettype" : [ "" ],
					"patching_rect" : [ 803.0, 352.0, 50.0, 18.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "m128-400",
					"linecount" : 2,
					"fontname" : "Andale Mono",
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-25",
					"outlettype" : [ "" ],
					"patching_rect" : [ 732.0, 341.0, 50.0, 31.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "outlet",
					"numinlets" : 1,
					"numoutlets" : 0,
					"id" : "obj-24",
					"patching_rect" : [ 1240.0, 475.0, 25.0, 25.0 ],
					"comment" : "Bang when disconnected"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "outlet",
					"numinlets" : 1,
					"numoutlets" : 0,
					"id" : "obj-22",
					"patching_rect" : [ 1269.0, 474.0, 25.0, 25.0 ],
					"comment" : "Bang when connected"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "set not connected",
					"fontname" : "Andale Mono",
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-19",
					"outlettype" : [ "" ],
					"patching_rect" : [ 392.0, 845.0, 133.0, 18.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "route /sys/id /sys/size /sys/host /sys/port /sys/prefix /sys/rotation",
					"fontname" : "Andale Mono",
					"numinlets" : 1,
					"numoutlets" : 7,
					"fontsize" : 12.0,
					"id" : "obj-18",
					"outlettype" : [ "", "", "", "", "", "", "" ],
					"patching_rect" : [ 730.0, 308.0, 562.0, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "port $1",
					"fontname" : "Andale Mono",
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-14",
					"outlettype" : [ "" ],
					"patching_rect" : [ 887.0, 100.0, 61.0, 18.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "udpreceive 17812",
					"fontname" : "Andale Mono",
					"numinlets" : 1,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-11",
					"outlettype" : [ "" ],
					"patching_rect" : [ 875.0, 251.0, 126.0, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "set",
					"fontname" : "Andale Mono",
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-10",
					"outlettype" : [ "" ],
					"patching_rect" : [ 355.0, 845.0, 32.5, 18.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "loadbang",
					"fontname" : "Andale Mono",
					"numinlets" : 1,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-63",
					"outlettype" : [ "bang" ],
					"patching_rect" : [ 10.0, 236.0, 68.0, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"id" : "obj-46",
					"outlettype" : [ "bang" ],
					"patching_rect" : [ 62.0, 265.0, 20.0, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "route /sys/prefix /sys/port port",
					"fontname" : "Andale Mono",
					"numinlets" : 1,
					"numoutlets" : 4,
					"fontsize" : 12.0,
					"id" : "obj-61",
					"outlettype" : [ "", "", "", "" ],
					"patching_rect" : [ 96.0, 844.0, 241.0, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "outlet",
					"numinlets" : 1,
					"numoutlets" : 0,
					"id" : "obj-55",
					"patching_rect" : [ 121.0, 886.0, 25.0, 25.0 ],
					"comment" : "application port"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "sprintf script newobject newobj @text \\\"zeroconf.service @name %s @port %s @type _osc._udp\\\"",
					"linecount" : 2,
					"fontname" : "Andale Mono",
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-50",
					"outlettype" : [ "" ],
					"patching_rect" : [ 847.0, 1041.0, 594.0, 33.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "thispatcher",
					"fontname" : "Andale Mono",
					"numinlets" : 1,
					"numoutlets" : 2,
					"fontsize" : 12.0,
					"id" : "obj-47",
					"outlettype" : [ "", "" ],
					"patching_rect" : [ 1007.0, 1089.0, 90.0, 20.0 ],
					"save" : [ "#N", "thispatcher", ";", "#Q", "end", ";" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "/sys/info",
					"fontname" : "Andale Mono",
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-54",
					"outlettype" : [ "" ],
					"patching_rect" : [ 16.0, 646.0, 75.0, 18.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "not connected",
					"presentation" : 1,
					"fontname" : "Andale Mono",
					"numinlets" : 1,
					"presentation_rect" : [ 230.0, 11.365231, 307.0, 19.0 ],
					"numoutlets" : 0,
					"fontsize" : 10.989406,
					"id" : "obj-44",
					"patching_rect" : [ 584.0, 934.971802, 236.0, 19.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"presentation" : 1,
					"fontname" : "Andale Mono",
					"numinlets" : 1,
					"presentation_rect" : [ 230.0, 17.21299, 306.0, 19.0 ],
					"numoutlets" : 0,
					"fontsize" : 11.294238,
					"id" : "obj-38",
					"patching_rect" : [ 585.0, 953.971802, 196.0, 19.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "textbutton",
					"presentation" : 1,
					"rounded" : 4.0,
					"bgoncolor" : [ 0.658824, 0.658824, 0.658824, 1.0 ],
					"bgcolor" : [ 1.0, 1.0, 1.0, 1.0 ],
					"textoncolor" : [ 0.0, 0.0, 0.0, 1.0 ],
					"fontname" : "Andale Mono",
					"numinlets" : 1,
					"presentation_rect" : [ 154.0, 12.0, 68.0, 17.0 ],
					"bgovercolor" : [ 0.8, 0.8, 0.8, 1.0 ],
					"numoutlets" : 3,
					"text" : "connect",
					"fontsize" : 9.0,
					"textoveroncolor" : [ 0.0, 0.0, 0.0, 1.0 ],
					"id" : "obj-5",
					"outlettype" : [ "", "", "int" ],
					"patching_rect" : [ 195.0, 299.0, 68.0, 17.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "t b b",
					"fontname" : "Andale Mono",
					"numinlets" : 1,
					"numoutlets" : 2,
					"fontsize" : 12.0,
					"id" : "obj-9",
					"outlettype" : [ "bang", "bang" ],
					"patching_rect" : [ 208.0, 358.0, 47.0, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "\"monome 128 (m128-400)\"",
					"linecount" : 2,
					"fontname" : "Andale Mono",
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-23",
					"outlettype" : [ "" ],
					"patching_rect" : [ 112.0, 470.0, 113.0, 31.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"id" : "obj-12",
					"outlettype" : [ "bang" ],
					"patching_rect" : [ 112.0, 422.0, 20.0, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "/sys/prefix #1",
					"fontname" : "Andale Mono",
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-49",
					"outlettype" : [ "" ],
					"patching_rect" : [ 96.0, 646.0, 111.0, 18.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"presentation" : 1,
					"fontname" : "Andale Mono",
					"numinlets" : 1,
					"presentation_rect" : [ 230.0, 6.222127, 307.0, 19.0 ],
					"numoutlets" : 0,
					"fontsize" : 11.326594,
					"id" : "obj-33",
					"patching_rect" : [ 584.0, 914.971802, 174.0, 19.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "t b b s",
					"fontname" : "Andale Mono",
					"numinlets" : 1,
					"numoutlets" : 3,
					"fontsize" : 12.0,
					"id" : "obj-29",
					"outlettype" : [ "bang", "bang", "" ],
					"patching_rect" : [ 112.0, 582.0, 61.0, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "/sys/port 15856",
					"fontname" : "Andale Mono",
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-26",
					"outlettype" : [ "" ],
					"patching_rect" : [ 256.0, 646.0, 119.0, 18.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "name $1, bang",
					"fontname" : "Andale Mono",
					"numinlets" : 2,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-13",
					"outlettype" : [ "" ],
					"patching_rect" : [ 112.0, 507.0, 104.0, 18.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "udpsend localhost 17812",
					"fontname" : "Andale Mono",
					"numinlets" : 1,
					"numoutlets" : 0,
					"fontsize" : 12.0,
					"id" : "obj-16",
					"patching_rect" : [ 174.0, 793.0, 180.0, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "zeroconf.resolve @domain local. @type _monome-osc._udp",
					"fontname" : "Andale Mono",
					"numinlets" : 1,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-6",
					"outlettype" : [ "" ],
					"patching_rect" : [ 112.0, 534.0, 399.0, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "umenu",
					"presentation" : 1,
					"rounded" : 4,
					"textcolor" : [ 0.0, 0.0, 0.0, 1.0 ],
					"align" : 1,
					"fontname" : "Andale Mono",
					"arrowcolor" : [ 1.0, 1.0, 1.0, 1.0 ],
					"framecolor" : [ 0.25098, 0.25098, 0.25098, 1.0 ],
					"items" : [ "monome 128 (m128-400)", ",", "monome 256 (m0000146)" ],
					"numinlets" : 1,
					"presentation_rect" : [ 13.0, 11.674528, 135.0, 17.0 ],
					"numoutlets" : 3,
					"fontsize" : 9.0,
					"id" : "obj-2",
					"types" : [  ],
					"arrowframe" : 0,
					"outlettype" : [ "int", "", "" ],
					"patching_rect" : [ 85.0, 299.0, 106.0, 17.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "zeroconf.browser @type _monome-osc._udp @domain local.",
					"fontname" : "Andale Mono",
					"numinlets" : 1,
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"id" : "obj-1",
					"outlettype" : [ "" ],
					"patching_rect" : [ 96.0, 246.0, 399.0, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "panel",
					"numinlets" : 1,
					"numoutlets" : 0,
					"id" : "obj-71",
					"patching_rect" : [ 534.0, 42.0, 566.0, 198.0 ]
				}

			}
 ],
		"lines" : [ 			{
				"patchline" : 				{
					"source" : [ "obj-70", 0 ],
					"destination" : [ "obj-62", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-52", 0 ],
					"destination" : [ "obj-70", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-51", 0 ],
					"destination" : [ "obj-15", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-21", 0 ],
					"destination" : [ "obj-50", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-7", 0 ],
					"destination" : [ "obj-21", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-59", 0 ],
					"destination" : [ "obj-15", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-20", 0 ],
					"destination" : [ "obj-26", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-7", 0 ],
					"destination" : [ "obj-20", 0 ],
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
					"source" : [ "obj-17", 0 ],
					"destination" : [ "obj-15", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-10", 0 ],
					"destination" : [ "obj-4", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-61", 0 ],
					"destination" : [ "obj-4", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-10", 0 ],
					"destination" : [ "obj-3", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-61", 1 ],
					"destination" : [ "obj-3", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-29", 0 ],
					"destination" : [ "obj-54", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-12", 0 ],
					"destination" : [ "obj-23", 0 ],
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
					"source" : [ "obj-2", 1 ],
					"destination" : [ "obj-23", 1 ],
					"hidden" : 1,
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
					"source" : [ "obj-1", 0 ],
					"destination" : [ "obj-2", 0 ],
					"hidden" : 1,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-26", 0 ],
					"destination" : [ "obj-16", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-6", 0 ],
					"destination" : [ "obj-29", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-29", 2 ],
					"destination" : [ "obj-16", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-29", 1 ],
					"destination" : [ "obj-26", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-49", 0 ],
					"destination" : [ "obj-16", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-29", 0 ],
					"destination" : [ "obj-49", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-9", 0 ],
					"destination" : [ "obj-12", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-9", 1 ],
					"destination" : [ "obj-2", 0 ],
					"hidden" : 1,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-5", 0 ],
					"destination" : [ "obj-9", 0 ],
					"hidden" : 1,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-54", 0 ],
					"destination" : [ "obj-16", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-26", 0 ],
					"destination" : [ "obj-61", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-29", 2 ],
					"destination" : [ "obj-61", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-49", 0 ],
					"destination" : [ "obj-61", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-61", 2 ],
					"destination" : [ "obj-55", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-46", 0 ],
					"destination" : [ "obj-2", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-63", 0 ],
					"destination" : [ "obj-46", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-50", 0 ],
					"destination" : [ "obj-47", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-14", 0 ],
					"destination" : [ "obj-11", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-11", 0 ],
					"destination" : [ "obj-18", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-10", 0 ],
					"destination" : [ "obj-33", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-10", 0 ],
					"destination" : [ "obj-38", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-19", 0 ],
					"destination" : [ "obj-44", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-18", 0 ],
					"destination" : [ "obj-25", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-18", 1 ],
					"destination" : [ "obj-27", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-18", 2 ],
					"destination" : [ "obj-28", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-18", 3 ],
					"destination" : [ "obj-30", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-18", 4 ],
					"destination" : [ "obj-31", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-18", 5 ],
					"destination" : [ "obj-32", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-9", 1 ],
					"destination" : [ "obj-34", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-34", 0 ],
					"destination" : [ "obj-25", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-34", 0 ],
					"destination" : [ "obj-27", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-34", 0 ],
					"destination" : [ "obj-28", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-34", 0 ],
					"destination" : [ "obj-30", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-34", 0 ],
					"destination" : [ "obj-31", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-34", 0 ],
					"destination" : [ "obj-32", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-35", 0 ],
					"destination" : [ "obj-22", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-9", 1 ],
					"destination" : [ "obj-24", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-18", 0 ],
					"destination" : [ "obj-36", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-18", 1 ],
					"destination" : [ "obj-37", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-18", 2 ],
					"destination" : [ "obj-39", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-18", 3 ],
					"destination" : [ "obj-40", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-18", 4 ],
					"destination" : [ "obj-41", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-18", 5 ],
					"destination" : [ "obj-42", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-18", 0 ],
					"destination" : [ "obj-43", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-43", 0 ],
					"destination" : [ "obj-33", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-18", 0 ],
					"destination" : [ "obj-48", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-48", 0 ],
					"destination" : [ "obj-44", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-18", 4 ],
					"destination" : [ "obj-45", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-45", 0 ],
					"destination" : [ "obj-38", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-14", 0 ],
					"destination" : [ "obj-57", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-53", 0 ],
					"destination" : [ "obj-58", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-58", 0 ],
					"destination" : [ "obj-59", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-59", 0 ],
					"destination" : [ "obj-57", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-57", 0 ],
					"destination" : [ "obj-11", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-60", 0 ],
					"destination" : [ "obj-62", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-62", 0 ],
					"destination" : [ "obj-11", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-61", 1 ],
					"destination" : [ "obj-64", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-64", 0 ],
					"destination" : [ "obj-14", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-46", 0 ],
					"destination" : [ "obj-66", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-66", 0 ],
					"destination" : [ "obj-64", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-59", 0 ],
					"destination" : [ "obj-66", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-60", 0 ],
					"destination" : [ "obj-67", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-67", 0 ],
					"destination" : [ "obj-64", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-46", 0 ],
					"destination" : [ "obj-68", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-9", 1 ],
					"destination" : [ "obj-68", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-68", 0 ],
					"destination" : [ "obj-10", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-68", 0 ],
					"destination" : [ "obj-19", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-61", 2 ],
					"destination" : [ "obj-69", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-10", 0 ],
					"destination" : [ "obj-69", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-8", 0 ],
					"destination" : [ "obj-65", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-65", 0 ],
					"destination" : [ "obj-7", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-58", 1 ],
					"destination" : [ "obj-60", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-60", 0 ],
					"destination" : [ "obj-65", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-18", 4 ],
					"destination" : [ "obj-35", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-64", 0 ],
					"destination" : [ "obj-52", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
 ]
	}

}

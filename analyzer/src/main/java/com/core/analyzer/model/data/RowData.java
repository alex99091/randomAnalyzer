package com.core.analyzer.model.data;

public class RowData {

    private static final String rowData = """
         1 10 23 29 33 37 40
         2 9 13 21 25 32 42
         3 11 16 19 21 27 31
         4 14 27 30 31 40 42
         5 16 24 29 40 41 42
         6 14 15 26 27 40 42
         7 2 9 16 25 26 40
         8 8 19 25 34 37 39
         9 2 4 16 17 36 39
         10 9 25 30 33 41 44
         11 1 7 36 37 41 42
         12 2 11 21 25 39 45
         13 22 23 25 37 38 42
         14 2 6 12 31 33 40
         15 3 4 16 30 31 37
         16 6 7 24 37 38 40
         17 3 4 9 17 32 37
         18 3 12 13 19 32 35
         19 6 30 38 39 40 43
         20 10 14 18 20 23 30
         21 6 12 17 18 31 32
         22 4 5 6 8 17 39
         23 5 13 17 18 33 42
         24 7 8 27 29 36 43
         25 2 4 21 26 43 44
         26 4 5 7 18 20 25
         27 1 20 26 28 37 43
         28 9 18 23 25 35 37
         29 1 5 13 34 39 40
         30 8 17 20 35 36 44
         31 7 9 18 23 28 35
         32 6 14 19 25 34 44
         33 4 7 32 33 40 41
         34 9 26 35 37 40 42
         35 2 3 11 26 37 43
         36 1 10 23 26 28 40
         37 7 27 30 33 35 37
         38 16 17 22 30 37 43
         39 6 7 13 15 21 43
         40 7 13 18 19 25 26
         41 13 20 23 35 38 43
         42 17 18 19 21 23 32
         43 6 31 35 38 39 44
         44 3 11 21 30 38 45
         45 1 10 20 27 33 35
         46 8 13 15 23 31 38
         47 14 17 26 31 36 45
         48 6 10 18 26 37 38
         49 4 7 16 19 33 40
         50 2 10 12 15 22 44
         51 2 3 11 16 26 44
         52 2 4 15 16 20 29
         53 7 8 14 32 33 39
         54 1 8 21 27 36 39
         55 17 21 31 37 40 44
         56 10 14 30 31 33 37
         57 7 10 16 25 29 44
         58 10 24 25 33 40 44
         59 6 29 36 39 41 45
         60 2 8 25 36 39 42
         61 14 15 19 30 38 43
         62 3 8 15 27 29 35
         63 3 20 23 36 38 40
         64 14 15 18 21 26 36
         65 4 25 33 36 40 43
         66 2 3 7 17 22 24
         67 3 7 10 15 36 38
         68 10 12 15 16 26 39
         69 5 8 14 15 19 39
         70 5 19 22 25 28 43
         71 5 9 12 16 29 41
         72 2 4 11 17 26 27
         73 3 12 18 32 40 43
         74 6 15 17 18 35 40
         75 2 5 24 32 34 44
         76 1 3 15 22 25 37
         77 2 18 29 32 43 44
         78 10 13 25 29 33 35
         79 3 12 24 27 30 32
         80 17 18 24 25 26 30
         81 5 7 11 13 20 33
         82 1 2 3 14 27 42
         83 6 10 15 17 19 34
         84 16 23 27 34 42 45
         85 6 8 13 23 31 36
         86 2 12 37 39 41 45
         87 4 12 16 23 34 43
         88 1 17 20 24 30 41
         89 4 26 28 29 33 40
         90 17 20 29 35 38 44
         91 1 21 24 26 29 42
         92 3 14 24 33 35 36
         93 6 22 24 36 38 44
         94 5 32 34 40 41 45
         95 8 17 27 31 34 43
         96 1 3 8 21 22 31
         97 6 7 14 15 20 36
         98 6 9 16 23 24 32
         99 1 3 10 27 29 37
         100 1 7 11 23 37 42
         101 1 3 17 32 35 45
         102 17 22 24 26 35 40
         103 5 14 15 27 30 45
         104 17 32 33 34 42 44
         105 8 10 20 34 41 45
         106 4 10 12 22 24 33
         107 1 4 5 6 9 31
         108 7 18 22 23 29 44
         109 1 5 34 36 42 44
         110 7 20 22 23 29 43
         111 7 18 31 33 36 40
         112 26 29 30 33 41 42
         113 4 9 28 33 36 45
         114 11 14 19 26 28 41
         115 1 2 6 9 25 28
         116 2 4 25 31 34 37
         117 5 10 22 34 36 44
         118 3 4 10 17 19 22
         119 3 11 13 14 17 21
         120 4 6 10 11 32 37
         121 12 28 30 34 38 43
         122 1 11 16 17 36 40
         123 7 17 18 28 30 45
         124 4 16 23 25 29 42
         125 2 8 32 33 35 36
         126 7 20 22 27 40 43
         127 3 5 10 29 32 43
         128 12 30 34 36 37 45
         129 19 23 25 28 38 42
         130 7 19 24 27 42 45
         131 8 10 11 14 15 21
         132 3 17 23 34 41 45
         133 4 7 15 18 23 26
         134 3 12 20 23 31 35
         135 6 14 22 28 35 39
         136 2 16 30 36 41 42
         137 7 9 20 25 36 39
         138 10 11 27 28 37 39
         139 9 11 15 20 28 43
         140 3 13 17 18 19 28
         141 8 12 29 31 42 43
         142 12 16 30 34 40 44
         143 26 27 28 42 43 45
         144 4 15 17 26 36 37
         145 2 3 13 20 27 44
         146 2 19 27 35 41 42
         147 4 6 13 21 40 42
         148 21 25 33 34 35 36
         149 2 11 21 34 41 42
         150 2 18 25 28 37 39
         151 1 2 10 13 18 19
         152 1 5 13 26 29 34
         153 3 8 11 12 13 36
         154 6 19 21 35 40 45
         155 16 19 20 32 33 41
         156 5 18 28 30 42 45
         157 19 26 30 33 35 39
         158 4 9 13 18 21 34
         159 1 18 30 41 42 43
         160 3 7 8 34 39 41
         161 22 34 36 40 42 45
         162 1 5 21 25 38 41
         163 7 11 26 28 29 44
         164 6 9 10 11 39 41
         165 5 13 18 19 22 42
         166 9 12 27 36 39 45
         167 24 27 28 30 36 39
         168 3 10 31 40 42 43
         169 16 27 35 37 43 45
         170 2 11 13 15 31 42
         171 4 16 25 29 34 35
         172 4 19 21 24 26 41
         173 3 9 24 30 33 34
         174 13 14 18 22 35 39
         175 19 26 28 31 33 36
         176 4 17 30 32 33 34
         177 1 10 13 16 37 43
         178 1 5 11 12 18 23
         179 5 9 17 25 39 43
         180 2 15 20 21 29 34
         181 14 21 23 32 40 45
         182 13 15 27 29 34 40
         183 2 18 24 34 40 42
         184 1 2 6 16 20 33
         185 1 2 4 8 19 38
         186 4 10 14 19 21 45
         187 1 2 8 18 29 38
         188 19 24 27 30 31 34
         189 8 14 32 35 37 45
         190 8 14 18 30 31 44
         191 5 6 24 25 32 37
         192 4 8 11 18 37 45
         193 6 14 18 26 36 39
         194 15 20 23 26 39 44
         195 7 10 19 22 35 40
         196 35 36 37 41 44 45
         197 7 12 16 34 42 45
         198 12 19 20 25 41 45
         199 14 21 22 25 30 36
         200 5 6 13 14 17 20
         201 3 11 24 38 39 44
         202 12 14 27 33 39 44
         203 1 3 11 24 30 32
         204 3 12 14 35 40 45
         205 1 3 21 29 35 37
         206 1 2 3 15 20 25
         207 3 11 14 31 32 37
         208 14 25 31 34 40 44
         209 2 7 18 20 24 33
         210 10 19 22 23 25 37
         211 12 13 17 20 33 41
         212 11 12 18 21 31 38
         213 2 3 4 5 20 24
         214 5 7 20 25 28 37
         215 2 3 7 15 43 44
         216 7 16 17 33 36 40
         217 16 20 27 33 35 39
         218 1 8 14 18 29 44
         219 4 11 20 26 35 37
         220 5 11 19 21 34 43
         221 2 20 33 35 37 40
         222 5 7 28 29 39 43
         223 1 3 18 20 26 27
         224 4 19 26 27 30 42
         225 5 11 13 19 31 36
         226 2 6 8 14 21 22
         227 4 5 15 16 22 42
         228 17 25 35 36 39 44
         229 4 5 9 11 23 38
         230 5 11 14 29 32 33
         231 5 10 19 31 44 45
         232 8 9 10 12 24 44
         233 4 6 13 17 28 40
         234 13 21 22 24 26 37
         235 21 22 26 27 31 37
         236 1 4 8 13 37 39
         237 1 11 17 21 24 44
         238 2 4 15 28 31 34
         239 11 15 24 39 41 44
         240 6 10 16 40 41 43
         241 2 16 24 27 28 35
         242 4 19 20 21 32 34
         243 2 12 17 19 28 42
         244 13 16 25 36 37 38
         245 9 11 27 31 32 38
         246 13 18 21 23 26 39
         247 12 15 28 36 39 40
         248 3 8 17 23 38 45
         249 3 8 27 31 41 44
         250 19 23 30 37 43 45
         251 6 7 19 25 28 38
         252 14 23 26 31 39 45
         253 8 19 25 31 34 36
         254 1 5 19 20 24 30
         255 1 5 6 24 27 42
         256 4 11 14 21 23 43
         257 6 13 27 31 32 37
         258 14 27 30 31 38 40
         259 4 5 14 35 42 45
         260 7 12 15 24 37 40
         261 6 11 16 18 31 43
         262 9 12 24 25 29 31
         263 1 27 28 32 37 40
         264 9 16 27 36 41 44
         265 5 9 34 37 38 39
         266 3 4 9 11 22 42
         267 7 8 24 34 36 41
         268 3 10 19 24 32 45
         269 5 18 20 36 42 43
         270 5 9 12 20 21 26
         271 3 8 9 27 29 40
         272 7 9 12 27 39 43
         273 1 8 24 31 34 44
         274 13 14 15 26 35 39
         275 14 19 20 35 38 40
         276 4 15 21 33 39 41
         277 10 12 13 15 25 29
         278 3 11 37 39 41 43
         279 7 16 31 36 37 38
         280 10 11 23 24 36 37
         281 1 3 4 6 14 41
         282 2 5 10 18 31 32
         283 6 8 18 31 38 45
         284 2 7 15 24 30 45
         285 13 33 37 40 41 45
         286 1 15 19 40 42 44
         287 6 12 24 27 35 37
         288 1 12 17 28 35 41
         289 3 14 33 37 38 42
         290 8 13 18 32 39 45
         291 3 7 8 18 20 42
         292 17 18 31 32 33 34
         293 1 9 17 21 29 33
         294 6 10 17 30 37 38
         295 1 4 12 16 18 38
         296 3 8 15 27 30 45
         297 6 11 19 20 28 32
         298 5 9 27 29 37 40
         299 1 3 20 25 36 45
         300 7 9 10 12 26 38
         301 7 11 13 33 37 43
         302 13 19 20 32 38 42
         303 2 14 17 30 38 45
         304 4 10 16 26 33 41
         305 7 8 18 21 23 39
         306 4 18 23 30 34 41
         307 5 15 21 23 25 45
         308 14 15 17 19 37 45
         309 1 2 5 11 18 36
         310 1 5 19 28 34 41
         311 4 12 24 27 28 32
         312 2 3 5 6 12 20
         313 9 17 34 35 43 45
         314 15 17 19 34 38 41
         315 1 13 33 35 43 45
         316 10 11 21 27 31 39
         317 3 10 11 22 36 39
         318 2 17 19 20 34 45
         319 5 8 22 28 33 42
         320 16 19 23 25 41 45
         321 12 18 20 21 25 34
         322 9 18 29 32 38 43
         323 10 14 15 32 36 42
         324 2 4 21 25 33 36
         325 7 17 20 32 44 45
         326 16 23 25 33 36 39
         327 6 12 13 17 32 44
         328 1 6 9 16 17 28
         329 9 17 19 30 35 42
         330 3 4 16 17 19 20
         331 4 9 14 26 31 44
         332 16 17 34 36 42 45
         333 5 14 27 30 39 43
         334 13 15 21 29 39 43
         335 5 9 16 23 26 45
         336 3 5 20 34 35 44
         337 1 5 14 18 32 37
         338 2 13 34 38 42 45
         339 6 8 14 21 30 37
         340 18 24 26 29 34 38
         341 1 8 19 34 39 43
         342 1 13 14 33 34 43
         343 1 10 17 29 31 43
         344 1 2 15 28 34 45
         345 15 20 23 29 39 42
         346 5 13 14 22 44 45
         347 3 8 13 27 32 42
         348 3 14 17 20 24 31
         349 5 13 14 20 24 25
         350 1 8 18 24 29 33
         351 5 25 27 29 34 36
         352 5 16 17 20 26 41
         353 11 16 19 22 29 36
         354 14 19 36 43 44 45
         355 5 8 29 30 35 44
         356 2 8 14 25 29 45
         357 10 14 18 21 36 37
         358 1 9 10 12 21 40
         359 1 10 19 20 24 40
         360 4 16 23 25 35 40
         361 5 10 16 24 27 35
         362 2 3 22 27 30 40
         363 11 12 14 21 32 38
         364 2 5 7 14 16 40
         365 5 15 21 25 26 30
         366 5 12 19 26 27 44
         367 3 22 25 29 32 44
         368 11 21 24 30 39 45
         369 17 20 35 36 41 43
         370 16 18 24 42 44 45
         371 7 9 15 26 27 42
         372 8 11 14 16 18 21
         373 15 26 37 42 43 45
         374 11 13 15 17 25 34
         375 4 8 19 25 27 45
         376 1 11 13 24 28 40
         377 6 22 29 37 43 45
         378 5 22 29 31 34 39
         379 6 10 22 31 35 40
         380 1 2 8 17 26 37
         381 1 5 10 12 16 20
         382 10 15 22 24 27 42
         383 4 15 28 33 37 40
         384 11 22 24 32 36 38
         385 7 12 19 21 29 32
         386 4 7 10 19 31 40
         387 1 26 31 34 40 43
         388 1 8 9 17 29 32
         389 7 16 18 20 23 26
         390 16 17 28 37 39 40
         391 10 11 18 22 28 39
         392 1 3 7 8 24 42
         393 9 16 28 40 41 43
         394 1 13 20 22 25 28
         395 11 15 20 26 31 35
         396 18 20 31 34 40 45
         397 12 13 17 22 25 33
         398 10 15 20 23 42 44
         399 1 2 9 17 19 42
         400 9 21 27 34 41 43
         401 6 12 18 31 38 43
         402 5 9 15 19 22 36
         403 10 14 22 24 28 37
         404 5 20 21 24 33 40
         405 1 2 10 25 26 44
         406 7 12 21 24 27 36
         407 6 7 13 16 24 25
         408 9 20 21 22 30 37
         409 6 9 21 31 32 40
         410 1 3 18 32 40 41
         411 11 14 22 35 37 39
         412 4 7 39 41 42 45
         413 2 9 15 23 34 40
         414 2 14 15 22 23 44
         415 7 17 20 26 30 40
         416 5 6 8 11 22 26
         417 4 5 14 20 22 43
         418 11 13 15 26 28 34
         419 2 11 13 14 28 30
         420 4 9 10 29 31 34
         421 6 11 26 27 28 44
         422 8 15 19 21 34 44
         423 1 17 27 28 29 40
         424 10 11 26 31 34 44
         425 8 10 14 27 33 38
         426 4 17 18 27 39 43
         427 6 7 15 24 28 30
         428 12 16 19 22 37 40
         429 3 23 28 34 39 42
         430 1 3 16 18 30 34
         431 18 22 25 31 38 45
         432 2 3 5 11 27 39
         433 19 23 29 33 35 43
         434 3 13 20 24 33 37
         435 8 16 26 30 38 45
         436 9 14 20 22 33 34
         437 11 16 29 38 41 44
         438 6 12 20 26 29 38
         439 17 20 30 31 37 40
         440 10 22 28 34 36 44
         441 1 23 28 30 34 35
         442 25 27 29 36 38 40
         443 4 6 10 19 20 44
         444 11 13 23 35 43 45
         445 13 20 21 30 39 45
         446 1 11 12 14 26 35
         447 2 7 8 9 17 33
         448 3 7 13 27 40 41
         449 3 10 20 26 35 43
         450 6 14 19 21 23 31
         451 12 15 20 24 30 38
         452 8 10 18 30 32 34
         453 12 24 33 38 40 42
         454 13 25 27 34 38 41
         455 4 19 20 26 30 35
         456 1 7 12 18 23 27
         457 8 10 18 23 27 40
         458 4 9 10 32 36 40
         459 4 6 10 14 25 40
         460 8 11 28 30 43 45
         461 11 18 26 31 37 40
         462 3 20 24 32 37 45
         463 23 29 31 33 34 44
         464 6 12 15 34 42 44
         465 1 8 11 13 22 38
         466 4 10 13 23 32 44
         467 2 12 14 17 24 40
         468 8 13 15 28 37 43
         469 4 21 22 34 37 38
         470 10 16 20 39 41 42
         471 6 13 29 37 39 41
         472 16 25 26 31 36 43
         473 8 13 20 22 23 36
         474 4 13 18 31 33 45
         475 1 9 14 16 21 29
         476 9 12 13 15 37 38
         477 14 25 29 32 33 45
         478 18 29 30 37 39 43
         479 8 23 25 27 35 44
         480 3 5 10 17 30 31
         481 3 4 23 29 40 41
         482 1 10 16 24 25 35
         483 12 15 19 22 28 34
         484 1 3 27 28 32 45
         485 17 22 26 27 36 39
         486 1 2 23 25 38 40
         487 4 8 25 27 37 41
         488 2 8 17 30 31 38
         489 2 4 8 15 20 27
         490 2 7 26 29 40 43
         491 8 17 35 36 39 42
         492 22 27 31 35 37 40
         493 20 22 26 33 36 37
         494 5 7 8 15 30 43
         495 4 13 22 27 34 44
         496 4 13 20 29 36 41
         497 19 20 23 24 43 44
         498 13 14 24 32 39 41
         499 5 20 23 27 35 40
         500 3 4 12 20 24 34
         501 1 4 10 17 31 42
         502 6 22 28 32 34 40
         503 1 5 27 30 34 36
         504 6 14 22 26 43 44
         505 7 20 22 25 38 40
         506 6 9 11 22 24 30
         507 12 13 32 33 40 41
         508 5 27 31 34 35 43
         509 12 25 29 35 42 43
         510 12 29 32 33 39 40
         511 3 7 14 23 26 42
         512 4 5 9 13 26 27
         513 5 8 21 23 27 33
         514 1 15 20 26 35 42
         515 2 11 12 15 23 37
         516 2 8 23 41 43 44
         517 1 9 12 28 36 41
         518 14 23 30 32 34 38
         519 6 8 13 16 30 43
         520 4 22 27 28 38 40
         521 3 7 18 29 32 36
         522 4 5 13 14 37 41
         523 1 4 37 38 40 45
         524 10 11 29 38 41 45
         525 11 23 26 29 39 44
         526 7 14 17 20 35 39
         527 1 12 22 32 33 42
         528 5 17 25 31 39 40
         529 18 20 24 27 31 42
         530 16 23 27 29 33 41
         531 1 5 9 21 27 35
         532 16 17 23 24 29 44
         533 9 14 15 17 31 33
         534 10 24 26 29 37 38
         535 11 12 14 15 18 39
         536 7 8 18 32 37 43
         537 12 23 26 30 36 43
         538 6 10 18 31 32 34
         539 3 19 22 31 42 43
         540 3 12 13 15 34 36
         541 8 13 26 28 32 34
         542 5 6 19 26 41 45
         543 13 18 26 31 34 44
         544 5 17 21 25 36 44
         545 4 24 25 27 34 35
         546 8 17 20 27 37 43
         547 6 7 15 22 34 39
         548 1 12 13 21 32 45
         549 29 31 35 38 40 44
         550 1 7 14 20 34 37
         551 3 6 20 24 27 44
         552 1 10 20 32 35 40
         553 2 7 17 28 29 39
         554 13 14 17 32 41 42
         555 11 17 21 24 26 36
         556 12 20 23 28 30 44
         557 4 20 26 28 35 40
         558 12 15 19 26 40 43
         559 11 12 25 32 44 45
         560 1 4 20 23 29 45
         561 5 7 18 37 42 45
         562 4 11 13 17 20 31
         563 5 10 16 17 31 32
         564 14 19 25 26 27 34
         565 4 10 18 27 40 45
         566 4 5 6 25 26 43
         567 1 10 15 16 32 41
         568 1 3 17 20 31 44
         569 3 6 13 23 24 35
         570 1 12 26 27 29 33
         571 11 18 21 26 38 43
         572 3 13 18 33 37 45
         573 2 4 20 34 35 43
         574 14 15 16 19 25 43
         575 2 8 20 30 33 34
         576 10 11 15 25 35 41
         577 16 17 22 31 34 37
         578 5 12 14 32 34 42
         579 5 7 20 22 37 42
         580 5 7 9 11 32 35
         581 3 5 14 20 42 44
         582 2 12 14 33 40 41
         583 8 17 27 33 40 44
         584 7 18 30 39 40 41
         585 6 7 10 16 38 41
         586 2 7 12 15 21 34
         587 14 21 29 31 32 37
         588 2 8 15 22 25 41
         589 6 8 28 33 38 39
         590 20 30 36 38 41 45
         591 8 13 14 30 38 39
         592 2 5 6 13 28 44
         593 9 10 13 24 33 38
         594 2 8 13 25 28 37
         595 8 24 28 35 38 40
         596 3 4 12 14 25 43
         597 8 10 23 24 35 43
         598 4 12 24 33 38 45
         599 5 12 17 29 34 35
         600 5 11 14 27 29 36
         601 2 16 19 31 34 35
         602 13 14 22 27 30 38
         603 2 19 25 26 27 43
         604 2 6 18 21 33 34
         605 1 2 7 9 10 38
         606 1 5 6 14 20 39
         607 8 14 23 36 38 39
         608 4 8 18 19 39 44
         609 4 8 27 34 39 40
         610 14 18 20 23 28 36
         611 2 22 27 33 36 37
         612 6 9 18 19 25 33
         613 7 8 11 16 41 44
         614 8 21 25 39 40 44
         615 10 17 18 19 23 27
         616 5 13 18 23 40 45
         617 4 5 11 12 24 27
         618 8 16 25 30 42 43
         619 6 8 13 30 35 40
         620 2 16 17 32 39 45
         621 1 2 6 16 19 42
         622 9 15 16 21 28 34
         623 7 13 30 39 41 45
         624 1 7 19 26 27 35
         625 3 6 7 20 21 39
         626 13 14 26 33 40 43
         627 2 9 22 25 31 45
         628 1 7 12 15 23 42
         629 19 28 31 38 43 44
         630 8 17 21 24 27 31
         631 1 2 4 23 31 34
         632 15 18 21 32 35 44
         633 9 12 19 20 39 41
         634 4 10 11 12 20 27
         635 11 13 25 26 29 33
         636 6 7 15 16 20 31
         637 3 16 22 37 38 44
         638 7 18 22 24 31 34
         639 6 15 22 23 25 32
         640 14 15 18 21 26 35
         641 11 18 21 36 37 43
         642 8 17 18 24 39 45
         643 15 24 31 32 33 40
         644 5 13 17 23 28 36
         645 1 4 16 26 40 41
         646 2 9 24 41 43 45
         647 5 16 21 23 24 30
         648 13 19 28 37 38 43
         649 3 21 22 33 41 42
         650 3 4 7 11 31 41
         651 11 12 16 26 29 44
         652 3 13 15 40 41 44
         653 5 6 26 27 38 39
         654 16 21 26 31 36 43
         655 7 37 38 39 40 44
         656 3 7 14 16 31 40
         657 10 14 19 39 40 43
         658 8 19 25 28 32 36
         659 7 18 19 27 29 42
         660 4 9 23 33 39 44
         661 2 3 12 20 27 38
         662 5 6 9 11 15 37
         663 3 5 8 19 38 42
         664 10 20 33 36 41 44
         665 5 6 11 17 38 44
         666 2 4 6 11 17 28
         667 15 17 25 37 42 43
         668 12 14 15 24 27 32
         669 7 8 20 29 33 38
         670 11 18 26 27 40 41
         671 7 9 10 13 31 35
         672 8 21 28 31 36 45
         673 7 10 17 29 33 44
         674 9 10 14 25 27 31
         675 1 8 11 15 18 45
         676 1 8 17 34 39 45
         677 12 15 24 36 41 44
         678 4 5 6 12 25 37
         679 3 5 7 14 26 34
         680 4 10 19 29 32 42
         681 21 24 27 29 43 44
         682 17 23 27 35 38 43
         683 6 13 20 27 28 40
         684 1 11 15 17 25 39
         685 6 7 12 28 38 40
         686 7 12 15 24 25 43
         687 1 8 10 13 28 42
         688 5 15 22 23 34 35
         689 7 17 19 30 36 38
         690 24 25 33 34 38 39
         691 15 27 33 35 43 45
         692 3 11 14 15 32 36
         693 1 6 11 28 34 42
         694 7 15 20 25 33 43
         695 4 18 26 33 34 38
         696 1 7 16 18 34 38
         697 2 5 8 11 33 39
         698 3 11 13 21 33 37
         699 4 5 8 16 21 29
         700 11 23 28 29 30 44
         701 3 10 14 16 36 38
         702 3 13 16 24 26 29
         703 10 28 31 33 41 44
         704 1 4 8 23 33 42
         705 1 6 17 22 28 45
         706 3 4 6 10 28 30
         707 2 12 19 24 39 44
         708 2 10 16 19 34 45
         709 10 18 30 36 39 44
         710 3 4 9 24 25 33
         711 11 15 24 35 37 45
         712 17 20 30 31 33 45
         713 2 5 15 18 19 23
         714 1 7 22 33 37 40
         715 2 7 27 33 41 44
         716 2 6 13 16 29 30
         717 2 11 19 25 28 32
         718 4 11 20 23 32 39
         719 4 8 13 19 20 43
         720 1 12 29 34 36 37
         721 1 28 35 41 43 44
         722 12 14 21 30 39 43
         723 20 30 33 35 36 44
         724 2 8 33 35 37 41
         725 6 7 19 21 41 43
         726 1 11 21 23 34 44
         727 7 8 10 19 21 31
         728 3 6 10 30 34 37
         729 11 17 21 26 36 45
         730 4 10 14 15 18 22
         731 2 7 13 25 42 45
         732 2 4 5 17 27 32
         733 11 24 32 33 35 40
         734 6 16 37 38 41 45
         735 5 10 13 27 37 41
         736 2 11 17 18 21 27
         737 13 15 18 24 27 41
         738 23 27 28 38 42 43
         739 7 22 29 33 34 35
         740 4 8 9 16 17 19
         741 5 21 27 34 44 45
         742 8 10 13 36 37 40
         743 15 19 21 34 41 44
         744 10 15 18 21 34 41
         745 1 2 3 9 12 23
         746 3 12 33 36 42 45
         747 7 9 12 14 23 28
         748 3 10 13 22 31 32
         749 12 14 24 26 34 45
         750 1 2 15 19 24 36
         751 3 4 16 20 28 44
         752 4 16 20 33 40 43
         753 2 17 19 24 37 41
         754 2 8 17 24 29 31
         755 13 14 26 28 30 36
         756 10 14 16 18 27 28
         757 6 7 11 17 33 44
         758 5 9 12 30 39 43
         759 9 33 36 40 42 43
         760 10 22 27 31 42 43
         761 4 7 11 24 42 45
         762 1 3 12 21 26 41
         763 3 8 16 32 34 43
         764 7 22 24 31 34 36
         765 1 3 8 12 42 43
         766 9 30 34 35 39 41
         767 5 15 20 31 34 42
         768 7 27 29 30 38 44
         769 5 7 11 16 41 45
         770 1 9 12 23 39 43
         771 6 10 17 18 21 29
         772 5 6 11 14 21 41
         773 8 12 19 21 31 35
         774 12 15 18 28 34 42
         775 11 12 29 33 38 42
         776 8 9 18 21 28 40
         777 6 12 17 21 34 37
         778 6 21 35 36 37 41
         779 6 12 19 24 34 41
         780 15 17 19 21 27 45
         781 11 16 18 19 24 39
         782 6 18 31 34 38 45
         783 14 15 16 17 38 45
         784 3 10 23 24 31 39
         785 4 6 15 25 26 33
         786 12 15 16 20 24 30
         787 5 6 13 16 27 28
         788 2 10 11 19 35 39
         789 2 6 7 12 19 45
         790 3 8 19 27 30 41
         791 2 10 12 31 33 42
         792 2 7 19 25 29 36
         793 10 15 21 35 38 43
         794 6 7 18 19 30 38
         795 3 10 13 26 34 38
         796 1 21 26 36 40 41
         797 5 22 31 32 39 45
         798 2 10 14 22 32 36
         799 12 17 23 34 42 45
         800 1 4 10 12 28 45
         801 17 25 28 37 43 44
         802 10 11 12 18 24 42
         803 5 9 14 26 30 43
         804 1 10 13 26 32 36
         805 3 12 13 18 31 32
         806 14 20 23 31 37 38
         807 6 10 18 25 34 35
         808 15 21 31 32 41 43
         809 6 11 15 17 23 40
         810 5 10 13 21 39 43
         811 8 11 19 21 36 45
         812 1 3 12 14 16 43
         813 11 30 34 35 42 44
         814 2 21 28 38 42 45
         815 17 21 25 26 27 36
         816 12 18 19 29 31 39
         817 3 9 12 13 25 43
         818 14 15 25 28 29 30
         819 16 25 33 38 40 45
         820 10 21 22 30 35 42
         821 1 12 13 24 29 44
         822 9 18 20 24 27 36
         823 12 18 24 26 39 40
         824 7 9 24 29 34 38
         825 8 15 21 31 33 38
         826 13 16 24 25 33 36
         827 5 11 12 29 33 44
         828 4 7 13 29 31 39
         829 4 5 31 35 43 45
         830 5 6 16 18 37 38
         831 3 10 16 19 31 39
         832 13 14 19 26 40 43
         833 12 18 30 39 41 42
         834 6 8 18 35 42 43
         835 9 10 13 28 38 45
         836 1 9 11 14 26 28
         837 2 25 28 30 33 45
         838 9 14 17 33 36 38
         839 3 9 11 12 13 19
         840 2 4 11 28 29 43
         841 5 11 14 30 33 38
         842 14 26 32 36 39 42
         843 19 21 30 33 34 42
         844 7 8 13 15 33 45
         845 1 16 29 33 40 45
         846 5 18 30 41 43 45
         847 12 16 26 28 30 42
         848 1 2 16 22 38 39
         849 5 13 17 29 34 39
         850 16 20 24 28 36 39
         851 14 18 22 26 31 44
         852 11 17 28 30 33 35
         853 2 8 23 26 27 44
         854 20 25 31 32 36 43
         855 8 15 17 19 43 44
         856 10 24 40 41 43 44
         857 6 10 16 28 34 38
         858 9 13 32 38 39 43
         859 8 22 35 38 39 41
         860 4 8 18 25 27 32
         861 11 17 19 21 22 25
         862 10 34 38 40 42 43
         863 16 21 28 35 39 43
         864 3 7 10 13 25 36
         865 3 15 22 32 33 45
         866 9 15 29 34 37 39
         867 14 17 19 22 24 40
         868 12 17 28 41 43 44
         869 2 6 20 27 37 39
         870 21 25 30 32 40 42
         871 2 6 12 26 30 34
         872 2 4 30 32 33 43
         873 3 5 12 13 33 39
         874 1 15 19 23 28 42
         875 19 22 30 34 39 44
         876 5 16 21 26 34 42
         877 5 17 18 22 23 43
         878 2 6 11 16 25 31
         879 1 4 10 14 15 35
         880 7 17 19 23 24 45
         881 4 18 20 26 27 32
         882 18 34 39 43 44 45
         883 9 18 32 33 37 44
         884 4 14 23 28 37 45
         885 1 3 24 27 39 45
         886 19 23 28 37 42 45
         887 8 14 17 27 36 45
         888 3 7 12 31 34 38
         889 3 13 29 38 39 42
         890 1 4 14 18 29 37
         891 9 13 28 31 39 41
         892 4 9 17 18 26 42
         893 1 15 17 23 25 41
         894 19 32 37 40 41 43
         895 16 26 31 38 39 41
         896 5 12 25 26 38 45
         897 6 7 12 22 26 36
         898 18 21 28 35 37 42
         899 8 19 20 21 33 39
         900 7 13 16 18 35 38
         901 5 18 20 23 30 34
         902 7 19 23 24 36 39
         903 2 15 16 21 22 28
         904 2 6 8 26 43 45
         905 3 4 16 27 38 40
         906 2 5 14 28 31 32
         907 21 27 29 38 40 44
         908 3 16 21 22 23 44
         909 7 24 29 30 34 35
         910 1 11 17 27 35 39
         911 4 5 12 14 32 42
         912 5 8 18 21 22 38
         913 6 14 16 21 27 37
         914 16 19 24 33 42 44
         915 2 6 11 13 22 37
         916 6 21 22 32 35 36
         917 1 3 23 24 27 43
         918 7 11 12 31 33 38
         919 9 14 17 18 42 44
         920 2 3 26 33 34 43
         921 5 7 12 22 28 41
         922 2 6 13 17 27 43
         923 3 17 18 23 36 41
         924 3 11 34 42 43 44
         925 13 24 32 34 39 42
         926 10 16 18 20 25 31
         927 4 15 22 38 41 43
         928 3 4 10 20 28 44
         929 7 9 12 15 19 23
         930 8 21 25 38 39 44
         931 14 15 23 25 35 43
         932 1 6 15 36 37 38
         933 23 27 29 31 36 45
         934 1 3 30 33 36 39
         935 4 10 20 32 38 44
         936 7 11 13 17 18 29
         937 2 10 13 22 29 40
         938 4 8 10 16 31 36
         939 4 11 28 39 42 45
         940 3 15 20 22 24 41
         941 12 14 25 27 39 40
         942 10 12 18 35 42 43
         943 1 8 13 36 44 45
         944 2 13 16 19 32 33
         945 9 10 15 30 33 37
         946 9 18 19 30 34 40
         947 3 8 17 20 27 35
         948 13 18 30 31 38 41
         949 14 21 35 36 40 44
         950 3 4 15 22 28 40
         951 2 12 30 31 39 43
         952 4 12 22 24 33 41
         953 7 9 22 27 37 42
         954 1 9 26 28 30 41
         955 4 9 23 26 29 33
         956 10 11 20 21 25 41
         957 4 15 24 35 36 40
         958 2 9 10 16 35 37
         959 1 14 15 24 40 41
         960 2 18 24 30 32 45
         961 11 20 29 31 33 42
         962 1 18 28 31 34 43
         963 6 12 19 23 34 42
         964 6 21 36 38 39 43
         965 2 13 25 28 29 36
         966 1 21 25 29 34 37
         967 1 6 13 37 38 40
         968 2 5 12 14 24 39
         969 3 9 10 29 40 45
         970 9 11 16 21 28 36
         971 2 6 17 18 21 26
         972 3 6 17 23 37 39
         973 22 26 31 37 41 42
         974 1 2 11 16 39 44
         975 7 8 9 17 22 24
         976 4 12 14 25 35 37
         977 2 9 10 14 22 44
         978 1 7 15 32 34 42
         979 7 11 16 21 27 33
         980 3 13 16 23 24 35
         981 27 36 37 41 43 45
         982 5 7 13 20 21 44
         983 13 23 26 31 35 43
         984 3 10 23 35 36 37
         985 17 21 23 30 34 44
         986 7 10 16 28 41 42
         987 2 4 15 23 29 38
         988 2 13 20 30 31 41
         989 17 18 21 27 29 33
         990 2 4 25 26 36 37
         991 13 18 25 31 33 44
         992 12 20 26 33 44 45
         993 6 14 16 18 24 42
         994 1 3 8 24 27 35
         995 1 4 13 29 38 39
         996 6 11 15 24 32 39
         997 4 7 14 16 24 44
         998 13 17 18 20 42 45
         999 1 3 9 14 18 28
         1000 2 8 19 22 32 42
         1001 6 10 12 14 20 42
         1002 17 25 33 35 38 45
         1003 1 4 29 39 43 45
         1004 7 15 30 37 39 44
         1005 8 13 18 24 27 29
         1006 8 11 15 16 17 37
         1007 8 11 16 19 21 25
         1008 9 11 30 31 41 44
         1009 15 23 29 34 40 44
         1010 9 12 15 25 34 36
         1011 1 9 12 26 35 38
         1012 5 11 18 20 35 45
         1013 21 22 26 34 36 41
         1014 3 11 14 18 26 27
         1015 14 23 31 33 37 40
         1016 15 26 28 34 41 42
         1017 12 18 22 23 30 34
         1018 3 19 21 25 37 45
         1019 1 4 13 17 34 39
         1020 12 27 29 38 41 45
         1021 12 15 17 24 29 45
         1022 5 6 11 29 42 45
         1023 10 14 16 18 29 35
         1024 9 18 20 22 38 44
         1025 8 9 20 25 29 33
         1026 5 12 13 31 32 41
         1027 14 16 27 35 39 45
         1028 5 7 12 13 18 35
         1029 12 30 32 37 39 41
         1030 2 5 11 17 24 29
         1031 6 7 22 32 35 36
         1032 1 6 12 19 36 42
         1033 3 11 15 20 35 44
         1034 26 31 32 33 38 40
         1035 9 14 34 35 41 42
         1036 2 5 22 32 34 45
         1037 2 14 15 22 27 33
         1038 7 16 24 27 37 44
         1039 2 3 6 19 36 39
         1040 8 16 26 29 31 36
         1041 6 7 9 11 17 18
         1042 5 14 15 23 34 43
         1043 3 5 12 22 26 31
         1044 12 17 20 26 28 36
         1045 6 14 15 19 21 41
         1046 7 16 25 29 35 36
         1047 2 20 33 40 42 44
         1048 6 12 17 21 32 39
         1049 3 5 13 20 21 37
         1050 6 12 31 35 38 43
         1051 21 26 30 32 33 35
         1052 5 17 26 27 35 38
         1053 22 26 29 30 34 45
         1054 14 19 27 28 30 45
         1055 4 7 12 14 22 33
         1056 13 20 24 32 36 45
         1057 8 13 19 27 40 45
         1058 11 23 25 30 32 40
         1059 7 10 22 25 34 40
         1060 3 10 24 33 38 45
         1061 4 24 27 35 37 45
         1062 20 31 32 40 41 45
         1063 3 6 22 23 24 38
         1064 3 6 9 18 22 35
         1065 3 18 19 23 32 45
         1066 6 11 16 19 21 32
         1067 7 10 19 23 28 33
         1068 4 7 19 26 33 35
         1069 1 10 18 22 28 31
         1070 3 6 14 22 30 41
         1071 1 2 11 21 30 35
         1072 16 18 20 23 32 43
         1073 6 18 28 30 32 38
         1074 1 6 20 27 28 41
         1075 1 23 24 35 44 45
         1076 3 7 9 33 36 37
         1077 4 8 17 30 40 43
         1078 6 10 11 14 36 38
         1079 4 8 18 24 37 45
         1080 13 16 23 31 36 44
         1081 1 9 16 23 24 38
         1082 21 26 27 32 34 42
         1083 3 7 14 15 22 38
         1084 8 12 13 29 33 42
         1085 4 7 17 18 38 44
         1086 11 16 25 27 35 36
         1087 13 14 18 21 34 44
         1088 11 21 22 30 39 44
         1089 4 18 31 37 42 43
         1090 12 19 21 29 40 45
         1091 6 20 23 24 28 30
         1092 7 18 19 26 33 45
         1093 10 17 22 30 35 43
         1094 6 7 15 22 26 40
         1095 8 14 28 29 34 40
         1096 1 12 16 19 23 43
         1097 14 33 34 35 37 40
         1098 12 16 21 24 41 43
         1099 3 20 28 38 40 43
         1100 17 26 29 30 31 43
         1101 6 7 13 28 36 42
         1102 13 14 22 26 37 38
         1103 10 12 29 31 40 44
         1104 1 7 21 30 35 38
         1105 6 16 34 37 39 40
         1106 1 3 4 29 42 45
         1107 6 14 30 31 40 41
         1108 7 19 26 37 39 44
         1109 10 12 13 19 33 40
         1110 3 7 11 20 22 41
         1111 3 13 30 33 43 45
         1112 16 20 26 36 42 44
         1113 11 13 20 21 32 44
         1114 10 16 19 32 33 38
         1115 7 12 23 32 34 36
         1116 15 16 17 25 30 31
         1117 3 4 9 30 33 36
         1118 11 13 14 15 16 45
         1119 1 9 12 13 20 45
         1120 2 19 26 31 38 41
         1121 6 24 31 32 38 44
         1122 3 6 21 30 34 35
         1123 13 19 21 24 34 35
         1124 3 8 17 30 33 34
         1125 6 14 25 33 40 44
         1126 4 5 9 11 37 40
         1127 10 15 24 30 31 37
         1128 1 5 8 16 28 33
         1129 5 10 11 17 28 34
         1130 15 19 21 25 27 28
         1131 1 2 6 14 27 38
         1132 6 7 19 28 34 41
         1133 13 14 20 28 29 34
         1134 3 7 9 13 19 24
         1135 1 6 13 19 21 33
         1136 21 33 35 38 42 44
         1137 4 9 12 15 33 45
         1138 14 16 19 20 29 34
         1139 5 12 15 30 37 40
         1140 7 10 22 29 31 38
         1141 7 11 12 21 26 35
         1142 2 8 28 30 37 41
         1143 10 16 17 27 28 36
         1144 3 4 12 15 26 34
         1145 3 4 12 15 26 34
         1146 2 11 31 33 37 44
         1147 6 11 17 19 40 43
         1148 7 11 24 26 27 37
         1149 3 6 13 15 16 22
         1150 8 15 19 21 32 36
         1151 8 9 18 35 39 45
         1152 2 3 9 15 27 29
         1153 30 31 32 35 36 37
         1154 1 9 10 13 35 44
         1155 4 8 22 26 32 38
         1156 10 16 19 27 37 38
         1157 30 31 34 39 41 45
         1158 5 7 12 20 25 26
         1159 21 25 27 32 37 38
         1160 3 9 27 28 38 39
         1161 7 13 18 36 39 45
         1162 2 12 20 24 34 42
         1163 20 21 22 25 28 29
         1164 2 13 15 16 33 43
         1165 17 18 23 25 38 39
         1166 6 7 27 29 38 45
         1167 14 23 25 27 29 42
         1168 8 23 31 35 38 40
   """;

    public static String getRowData() {
        return rowData;
    }
}

package com.revisapp.domain;

import com.revisapp.domain.Matter;

/**
 * Created by juan_ on 06/09/2017.
 */


@in.cubestack.android.lib.storm.annotation.Database(
        name = "Revisapp",
        tables = {
                Matter.class, Photo.class},
        version = 1)
public class Database {

}
/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers;

import models.Game;
import ninja.Context;
import ninja.Result;
import ninja.Results;

import com.google.inject.Singleton;
import ninja.params.PathParam;


@Singleton
public class ApplicationController {

    public Result index() {

        return Results.html();

    }

    public Result blackjack() {
        return Results.html().template("views/BlackJack/BlackJack.flt.html");
    }

    public Result gameGet(){
        Game g = new Game();
        g.buildDeck();
        g.shuffle();

        return Results.json().render(g);
    }

    public Result dealPost(Context context, Game g) {
        if(g.isActive()==false) {
            g.newHand();
            g.deal(g.user);
            g.deal(g.dealer);
            g.deal(g.user);
            g.deal(g.dealer);
        }
        return Results.json().render(g);
    }

    public Result Hit(Context context, Game g){
        if(g.isActive() && g.user.sum<22) {
            g.deal(g.user);
        }
        while (!g.user.willHit()){
            EndTurn(context, g);
        }
        return Results.json().render(g);
    }

    public Result EndTurn(Context context, Game g){

        while(g.dealer.willHit()){
            g.deal(g.dealer);
        }
        g.WhoWins();
        return Results.json().render(g);
    }

    public Result Double(Context context, Game g){
        if(g.isActive() && !g.canDouble()) {
            g.doubleDown();
            Hit(context, g);
        }
        return Results.json().render(g);
    }

    public Result user2(Context context, Game g){
        if(g.canSplit()) {
            g.splithand(g.user);
        }
        g.deal(g.user);
        g.deal(g.p2);
        return Results.json().render(g);
    }
}

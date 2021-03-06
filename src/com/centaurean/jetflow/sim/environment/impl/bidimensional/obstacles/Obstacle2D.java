package com.centaurean.jetflow.sim.environment.impl.bidimensional.obstacles;

import com.centaurean.jetflow.sim.environment.obstacles.Obstacle;
import com.centaurean.jetflow.sim.environment.obstacles.ObstaclePart;
import com.centaurean.jetflow.sim.geometry.Coordinates;

import java.awt.*;
import java.util.LinkedList;

/*
 * Copyright (c) 2013, Centaurean software
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of Centaurean software nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Centaurean software BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * jetFlow
 *
 * 04/03/13 21:21
 * @author gpnuma
 */
public class Obstacle2D extends LinkedList<ObstaclePart> implements Obstacle {
    @Override
    public boolean includes(Coordinates coordinates) {
        for (ObstaclePart triangle : this)
            if (triangle.includes(coordinates))
                return true;
        return false;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(Color.darkGray);
        for (ObstaclePart part : this)
            part.draw(graphics2D);
    }
}

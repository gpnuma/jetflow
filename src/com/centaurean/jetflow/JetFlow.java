package com.centaurean.jetflow;

import com.centaurean.jetflow.sim.environment.impl.bidimensional.obstacles.Obstacle2D;
import com.centaurean.jetflow.sim.environment.impl.bidimensional.obstacles.ObstaclePart2D;
import com.centaurean.jetflow.sim.environment.impl.bidimensional.obstacles.Obstacles2D;
import com.centaurean.jetflow.sim.geometry.impl.bidimensional.Coordinates2D;
import com.centaurean.jetflow.sim.geometry.impl.bidimensional.Point2D;
import com.centaurean.jetflow.sim.solver.Mass;
import com.centaurean.jetflow.sim.solver.Solver;
import com.centaurean.jetflow.sim.solver.impl.cpu.bidimensional.Grid2D;
import com.centaurean.jetflow.sim.solver.impl.cpu.bidimensional.Particle2D;
import com.centaurean.jetflow.sim.solver.impl.cpu.bidimensional.Particles2D;
import com.centaurean.jetflow.sim.solver.impl.cpu.bidimensional.Solver2D;
import com.centaurean.jetflow.sim.ui.SimWindow;

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
 * 06/03/13 15:45
 * @author gpnuma
 */
public class JetFlow {
    public static final double SCALE = 0.0001;
    public static final int PARTICLES = 10000;
    public static final double TIME_STEP = 0.01;
    private static JetFlow instance = new JetFlow();
    private Solver solver;
    private SimWindow simWindow;

    private JetFlow() {
    }

    public static JetFlow getInstance() {
        return instance;
    }

    public static void main(String... args) {
        JetFlow jetFlow = getInstance();
        Solver2D solver = Solver2D.getInstance();

        // (density of fluid * total volume) / (total number of particles)
        Mass mass = new Mass(Particle2D.REST_DENSITY * Solver2D.WIDTH * Solver2D.HEIGHT / PARTICLES);
        System.out.println(mass);
        Particles2D particles = new Particles2D();
        Grid2D grid = new Grid2D();
        for (int i = 0; i < PARTICLES; i++) {
            Particle2D particle2D = new Particle2D(mass, new Coordinates2D(Math.random() * Solver2D.WIDTH, Math.random() * Solver2D.HEIGHT));
            particles.add(particle2D);
            grid.add(particle2D);
        }

        Obstacles2D obstacles = new Obstacles2D();
        Obstacle2D obstacle = new Obstacle2D();
        for (int i = 0; i < 10; i++)
            obstacle.add(new ObstaclePart2D(new Point2D(Math.random() * Solver2D.WIDTH, Math.random() * Solver2D.HEIGHT), new Point2D(Math.random() * Solver2D.WIDTH, Math.random() * Solver2D.HEIGHT), new Point2D(Math.random() * Solver2D.WIDTH, Math.random() * Solver2D.HEIGHT)));
        obstacles.add(obstacle);

        solver.setObstacles(obstacles);
        solver.setParticles(particles);
        solver.setGrid(grid);
        jetFlow.setSolver(solver);
        jetFlow.setSimWindow(SimWindow.getInstance());

        /*SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame simWindowFrame = new JFrame();
                simWindowFrame.getContentPane().add(SwingSimWindow.getInstance(), BorderLayout.CENTER);
                simWindowFrame.setSize(800, 800);
                simWindowFrame.setVisible(true);
            }
        });*/

        //solver.initialize();
        while (true)
            JetFlow.getInstance().getSolver().step();
    }

    public Solver getSolver() {
        return solver;
    }

    public void setSolver(Solver solver) {
        this.solver = solver;
    }

    public SimWindow getSimWindow() {
        return simWindow;
    }

    public void setSimWindow(SimWindow simWindow) {
        this.simWindow = simWindow;
    }
}

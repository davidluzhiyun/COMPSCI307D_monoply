## Description

When the player tries to start a new game including jail, the game won't start correctly.

## Expected Behavior

The game should work with jail.

## Current Behavior

The game won't work with jail.

## Steps to Reproduce

1. Prepare a .json file for starting from a new game, the game configurations should enable jail.
2. On the file selection screen, click "load/resume a game" and select that .json file.

## Failure Logs
Exception in thread "JavaFX Application Thread" java.lang.RuntimeException: java.lang.reflect.InvocationTargetException
at ooga.view.button.CustomizedButton.lambda$setAction$0(CustomizedButton.java:46)
at javafx.base/com.sun.javafx.event.CompositeEventHandler.dispatchBubblingEvent(CompositeEventHandler.java:86)
at javafx.base/com.sun.javafx.event.EventHandlerManager.dispatchBubblingEvent(EventHandlerManager.java:234)
at javafx.base/com.sun.javafx.event.EventHandlerManager.dispatchBubblingEvent(EventHandlerManager.java:191)
at javafx.base/com.sun.javafx.event.CompositeEventDispatcher.dispatchBubblingEvent(CompositeEventDispatcher.java:59)
at javafx.base/com.sun.javafx.event.BasicEventDispatcher.dispatchEvent(BasicEventDispatcher.java:58)
at javafx.base/com.sun.javafx.event.EventDispatchChainImpl.dispatchEvent(EventDispatchChainImpl.java:114)
at javafx.base/com.sun.javafx.event.BasicEventDispatcher.dispatchEvent(BasicEventDispatcher.java:56)
at javafx.base/com.sun.javafx.event.EventDispatchChainImpl.dispatchEvent(EventDispatchChainImpl.java:114)
at javafx.base/com.sun.javafx.event.BasicEventDispatcher.dispatchEvent(BasicEventDispatcher.java:56)
at javafx.base/com.sun.javafx.event.EventDispatchChainImpl.dispatchEvent(EventDispatchChainImpl.java:114)
at javafx.base/com.sun.javafx.event.EventUtil.fireEventImpl(EventUtil.java:74)
at javafx.base/com.sun.javafx.event.EventUtil.fireEvent(EventUtil.java:49)
at javafx.base/javafx.event.Event.fireEvent(Event.java:198)
at javafx.graphics/javafx.scene.Node.fireEvent(Node.java:8923)
at javafx.controls/javafx.scene.control.Button.fire(Button.java:203)
at javafx.controls/com.sun.javafx.scene.control.behavior.ButtonBehavior.mouseReleased(ButtonBehavior.java:207)
at javafx.controls/com.sun.javafx.scene.control.inputmap.InputMap.handle(InputMap.java:274)
at javafx.base/com.sun.javafx.event.CompositeEventHandler$NormalEventHandlerRecord.handleBubblingEvent(CompositeEventHandler.java:247)
at javafx.base/com.sun.javafx.event.CompositeEventHandler.dispatchBubblingEvent(CompositeEventHandler.java:80)
at javafx.base/com.sun.javafx.event.EventHandlerManager.dispatchBubblingEvent(EventHandlerManager.java:234)
at javafx.base/com.sun.javafx.event.EventHandlerManager.dispatchBubblingEvent(EventHandlerManager.java:191)
at javafx.base/com.sun.javafx.event.CompositeEventDispatcher.dispatchBubblingEvent(CompositeEventDispatcher.java:59)
at javafx.base/com.sun.javafx.event.BasicEventDispatcher.dispatchEvent(BasicEventDispatcher.java:58)
at javafx.base/com.sun.javafx.event.EventDispatchChainImpl.dispatchEvent(EventDispatchChainImpl.java:114)
at javafx.base/com.sun.javafx.event.BasicEventDispatcher.dispatchEvent(BasicEventDispatcher.java:56)
at javafx.base/com.sun.javafx.event.EventDispatchChainImpl.dispatchEvent(EventDispatchChainImpl.java:114)
at javafx.base/com.sun.javafx.event.BasicEventDispatcher.dispatchEvent(BasicEventDispatcher.java:56)
at javafx.base/com.sun.javafx.event.EventDispatchChainImpl.dispatchEvent(EventDispatchChainImpl.java:114)
at javafx.base/com.sun.javafx.event.EventUtil.fireEventImpl(EventUtil.java:74)
at javafx.base/com.sun.javafx.event.EventUtil.fireEvent(EventUtil.java:54)
at javafx.base/javafx.event.Event.fireEvent(Event.java:198)
at javafx.graphics/javafx.scene.Scene$MouseHandler.process(Scene.java:3894)
at javafx.graphics/javafx.scene.Scene.processMouseEvent(Scene.java:1887)
at javafx.graphics/javafx.scene.Scene$ScenePeerListener.mouseEvent(Scene.java:2620)
at javafx.graphics/com.sun.javafx.tk.quantum.GlassViewEventHandler$MouseEventNotification.run(GlassViewEventHandler.java:411)
at javafx.graphics/com.sun.javafx.tk.quantum.GlassViewEventHandler$MouseEventNotification.run(GlassViewEventHandler.java:301)
at java.base/java.security.AccessController.doPrivileged(AccessController.java:399)
at javafx.graphics/com.sun.javafx.tk.quantum.GlassViewEventHandler.lambda$handleMouseEvent$2(GlassViewEventHandler.java:450)
at javafx.graphics/com.sun.javafx.tk.quantum.QuantumToolkit.runWithoutRenderLock(QuantumToolkit.java:424)
at javafx.graphics/com.sun.javafx.tk.quantum.GlassViewEventHandler.handleMouseEvent(GlassViewEventHandler.java:449)
at javafx.graphics/com.sun.glass.ui.View.handleMouseEvent(View.java:551)
at javafx.graphics/com.sun.glass.ui.View.notifyMouse(View.java:937)
at javafx.graphics/com.sun.glass.ui.gtk.GtkApplication._runLoop(Native Method)
at javafx.graphics/com.sun.glass.ui.gtk.GtkApplication.lambda$runLoop$11(GtkApplication.java:316)
at java.base/java.lang.Thread.run(Thread.java:1589)
Caused by: java.lang.reflect.InvocationTargetException
at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:119)
at java.base/java.lang.reflect.Method.invoke(Method.java:578)
at ooga.view.button.CustomizedButton.lambda$setAction$0(CustomizedButton.java:44)
... 45 more
Caused by: java.lang.IllegalArgumentException: wrong number of arguments: 2 expected: 1
at java.base/jdk.internal.reflect.DirectConstructorHandleAccessor.newInstance(DirectConstructorHandleAccessor.java:64)
at java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500)
at java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:484)
at ooga.model.gamearchive.InitialConfigLoader.createPlace(InitialConfigLoader.java:90)
at ooga.model.gamearchive.InitialConfigLoader.loadData(InitialConfigLoader.java:62)
at ooga.model.gamearchive.InitialConfigLoader.<init>(InitialConfigLoader.java:43)
at ooga.model.GameModel.initializeGame(GameModel.java:97)
at ooga.model.GameModel.startGame(GameModel.java:225)
at ooga.model.GameModel.onGameEvent(GameModel.java:181)
at ooga.event.GameEventHandler.lambda$publish$0(GameEventHandler.java:24)
at java.base/java.util.concurrent.ConcurrentLinkedQueue.forEachFrom(ConcurrentLinkedQueue.java:1037)
at java.base/java.util.concurrent.ConcurrentLinkedQueue.forEach(ConcurrentLinkedQueue.java:1054)
at ooga.event.GameEventHandler.publish(GameEventHandler.java:24)
at ooga.controller.Controller.onGameEvent(Controller.java:60)
at ooga.event.GameEventHandler.lambda$publish$0(GameEventHandler.java:24)
at java.base/java.util.concurrent.ConcurrentLinkedQueue.forEachFrom(ConcurrentLinkedQueue.java:1037)
at java.base/java.util.concurrent.ConcurrentLinkedQueue.forEach(ConcurrentLinkedQueue.java:1054)
at ooga.event.GameEventHandler.publish(GameEventHandler.java:24)
at ooga.view.scene.GameSelectionScene.makeFileDialog(GameSelectionScene.java:128)
at ooga.view.scene.GameSelectionScene.startNewGame(GameSelectionScene.java:89)
at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:104)
... 47 more


## Hypothesis for Fixing the Bug

The constructor of ConcreteJail does not have correct arguments, and this causes problem to the factory code to initialize all the places.
It should be a easy fix to make the constructor consistent with other constructors of places.
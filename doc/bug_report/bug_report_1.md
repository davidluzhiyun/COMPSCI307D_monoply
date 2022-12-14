## Description

When the player tries to load/resume a game from a previously saved game, the game will not load correctly and will throw error.

## Expected Behavior

The game should restore to the previously saved game.

## Current Behavior

The board can show something, but the windows for choosing player avatars and rolling dice do not appear.

## Steps to Reproduce

1. Prepare a .json file of a previously saved game session to load (can be done by clicking the "save game" button when one runs the app)
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
at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:110)
at java.base/java.lang.reflect.Method.invoke(Method.java:578)
at ooga.view.button.CustomizedButton.lambda$setAction$0(CustomizedButton.java:44)
... 45 more
Caused by: java.lang.ClassCastException: class java.util.ArrayList cannot be cast to class com.google.gson.internal.LinkedTreeMap (java.util.ArrayList is in module java.base of loader 'bootstrap'; com.google.gson.internal.LinkedTreeMap is in unnamed module of loader 'app')
at ooga.event.eventRunnable.LoadBoardRunnable.makeRecord(LoadBoardRunnable.java:55)
at ooga.event.eventRunnable.LoadBoardRunnable.processEvent(LoadBoardRunnable.java:37)
at ooga.controller.Controller.onGameEvent(Controller.java:59)
at ooga.event.GameEventHandler.lambda$publish$0(GameEventHandler.java:24)
at java.base/java.util.concurrent.ConcurrentLinkedQueue.forEachFrom(ConcurrentLinkedQueue.java:1037)
at java.base/java.util.concurrent.ConcurrentLinkedQueue.forEach(ConcurrentLinkedQueue.java:1054)
at ooga.event.GameEventHandler.publish(GameEventHandler.java:24)
at ooga.view.scene.GameSelectionScene.makeFileDialog(GameSelectionScene.java:128)
at ooga.view.scene.GameSelectionScene.loadGame(GameSelectionScene.java:108)
at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:104)
... 47 more


## Hypothesis for Fixing the Bug

The view needs some information such as the row and column size of the board and the image of a place, which is not included in the model.
Therefore, the model will not save these information when clicking the "save game" button, and the saved .json file does include everything for the frontend to show the board.
This can be fixed by saving a copy of the initial configuration in the model (although some information is not needed for model), 
and write that into the saved .json file, so that the saved .json file include the extra information the frontend needs.

Besides, the frontend publishes a wrong event for loading/resuming a game. This can be fixed by examining the event loop between model, view, and MVC.

import processing.core.PApplet
import processing.core.PConstants

lateinit var p: PApplet
lateinit var player: Player
lateinit var background: Background
lateinit var enemies: Enemies
lateinit var gui: Gui

class Main : PApplet(){
    var keysPressed = mutableMapOf("left" to false, "right" to false, "up" to false, "down" to false, "space" to false)

    override fun settings(){
        fullScreen(2)
    }

    override fun setup(){
        p = this

        imageMode(CENTER)

        player = Player()
        background = Background()
        enemies = Enemies()
        gui = Gui()
    }


    override fun draw(){
        background(0)
        handleKeys()
        gui.update()
    }


    override fun keyPressed(){
        if (key.toInt() == PConstants.CODED) {
            when (keyCode) {
                PConstants.LEFT -> keysPressed["left"] = true
                PConstants.RIGHT -> keysPressed["right"] = true
                PConstants.UP -> keysPressed["up"] = true
                PConstants.DOWN -> keysPressed["down"] = true
            }

        } else {
            when (key) {
                'a' -> keysPressed["left"] = true
                'd' -> keysPressed["right"] = true
                'w' -> keysPressed["up"] = true
                's' -> {
                    keysPressed["down"] = true
                    if (gui.state == "menu") gui.state = "shop"
                }
                ' ' -> keysPressed["space"] = true

                'b' -> gui.state = "menu"
                'p' -> if (gui.state == "menu" || gui.state == "game over") gui.state = "game"
                'q' -> if (gui.state == "menu" || gui.state == "game over") gui.state = "quit"
                'i' -> if (gui.state == "menu") gui.state = "info"
            }
        }
    }

    override fun keyReleased(){
        if (key.toInt() == PConstants.CODED) {
            when (keyCode) {
                PConstants.LEFT -> keysPressed["left"] = false
                PConstants.RIGHT -> keysPressed["right"] = false
                PConstants.UP -> keysPressed["up"] = false
                PConstants.DOWN -> keysPressed["down"] = false
            }
        } else {
            when (key) {
                'a' -> keysPressed["left"] = false
                'd' -> keysPressed["right"] = false
                'w' -> keysPressed["up"] = false
                's' -> keysPressed["down"] = false
                ' ' -> keysPressed["space"] = false
            }
        }
    }


    override fun mousePressed() {
        if (p.mouseX > gui.bSpeed.x - gui.bSpeed.img.width / 2 && p.mouseX < gui.bSpeed.x + gui.bSpeed.img.width / 2 && p.mouseY > gui.bSpeed.y - gui.bSpeed.img.height / 2 && p.mouseY < gui.bSpeed.y + gui.bSpeed.img.height / 2) gui.bSpeed.onClick()
        else if (p.mouseX > gui.reload.x - gui.reload.img.width / 2 && p.mouseX < gui.reload.x + gui.reload.img.width / 2 && p.mouseY > gui.reload.y - gui.reload.img.height / 2 && p.mouseY < gui.reload.y + gui.reload.img.height / 2) gui.reload.onClick()
        else if (p.mouseX > gui.lGain.x - gui.lGain.img.width / 2 && p.mouseX < gui.lGain.x + gui.lGain.img.width / 2 && p.mouseY > gui.lGain.y - gui.lGain.img.height / 2 && p.mouseY < gui.lGain.y + gui.lGain.img.height / 2) gui.lGain.onClick()
        else if (p.mouseX > gui.mSpeed.x - gui.mSpeed.img.width / 2 && p.mouseX < gui.mSpeed.x + gui.mSpeed.img.width / 2 && p.mouseY > gui.mSpeed.y - gui.mSpeed.img.height / 2 && p.mouseY < gui.mSpeed.y + gui.mSpeed.img.height / 2) gui.mSpeed.onClick()
        else if (p.mouseX > gui.backBtn.x - gui.backBtn.img.width / 2 && p.mouseX < gui.backBtn.x + gui.backBtn.img.width / 2 && p.mouseY > gui.backBtn.y - gui.backBtn.img.height / 2 && p.mouseY < gui.backBtn.y + gui.backBtn.img.height / 2) gui.backBtn.onClick()
        else if (p.mouseX > gui.playBtn.x - gui.playBtn.img.width / 2 && p.mouseX < gui.playBtn.x + gui.playBtn.img.width / 2 && p.mouseY > gui.playBtn.y - gui.playBtn.img.height / 2 && p.mouseY < gui.playBtn.y + gui.playBtn.img.height / 2) gui.playBtn.onClick()
        else if (p.mouseX > gui.shopBtn.x - gui.shopBtn.img.width / 2 && p.mouseX < gui.shopBtn.x + gui.shopBtn.img.width / 2 && p.mouseY > gui.shopBtn.y - gui.shopBtn.img.height / 2 && p.mouseY < gui.shopBtn.y + gui.shopBtn.img.height / 2) gui.shopBtn.onClick()
        else if (p.mouseX > gui.quitBtn.x - gui.quitBtn.img.width / 2 && p.mouseX < gui.quitBtn.x + gui.quitBtn.img.width / 2 && p.mouseY > gui.quitBtn.y - gui.quitBtn.img.height / 2 && p.mouseY < gui.quitBtn.y + gui.quitBtn.img.height / 2) gui.quitBtn.onClick()
        else if (p.mouseX > gui.infoBtn.x - gui.infoBtn.img.width / 2 && p.mouseX < gui.infoBtn.x + gui.infoBtn.img.width / 2 && p.mouseY > gui.infoBtn.y - gui.infoBtn.img.height / 2 && p.mouseY < gui.infoBtn.y + gui.infoBtn.img.height / 2) gui.infoBtn.onClick()
    }

    private fun handleKeys(){
        if (keysPressed["left"]!!) player.x -= player.speed
        else if (keysPressed["right"]!!) player.x += player.speed
        if (keysPressed["up"]!!) player.y -= player.speed
        else if (keysPressed["down"]!!) player.y += player.speed
        if (keysPressed["space"]!!) player.tryShoot()
    }
}

fun whileRotated(x: Float, y: Float, radians: Float, block: () -> Unit) {
    p.pushMatrix()
    p.translate(x, y)
    p.rotate(radians)
    block()
    p.popMatrix()
}

fun main(){
    PApplet.main("Main")
}

fun map(value: Float, istart: Float, istop: Float, ostart: Float, ostop: Float) =
    ostart + (ostop - ostart) * ((value - istart) / (istop - istart))
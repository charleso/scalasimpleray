package ray

import swing.{ Panel, MainFrame, SimpleSwingApplication }
import java.awt.{ Color, Graphics2D, Dimension }
import java.awt.event._
import java.awt
import javax.swing.Timer
import swing._
import javax.swing.filechooser.FileFilter
import javax.swing.filechooser.FileNameExtensionFilter
import scala.util.Random

object ColorPanel extends SimpleSwingApplication {
  private var c: Color = new Color(0)
  private val frameTitle = "scalaray"

  var width: Int = 640
  var height: Int = 480

  val sceneFromJSON = new SceneParser().parseScene("scene.json")
  val t = new Tracer()
  val pixels = t.trace(sceneFromJSON, 640, 480)
  
  def top = new MainFrame {
    title = frameTitle
    contents = p
  }

  val p = new Panel with ActionListener {
    preferredSize = new Dimension(width, height)
    

    override def paintComponent(g: Graphics2D) {
      val dx = g.getClipBounds.width.toFloat / width
      val dy = g.getClipBounds.height.toFloat / height

      for {
        x <- pixels.s.indices
        y <- pixels.s(x).indices
      } {
        g.setColor(pixels.s(x)(y))
        g.fillRect(x, y, x, y)
      }
    }

    def actionPerformed(e: ActionEvent) {
      repaint
    }
  }
}
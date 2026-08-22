/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  l2.gameserver.templates.StatsSet
 */
package helpers;

import java.io.File;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import l2.gameserver.templates.StatsSet;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class XMLDocument {
    protected static final Logger LOGGER = Logger.getLogger(XMLDocument.class.getName());
    private static final DocumentBuilderFactory BUILDER = DocumentBuilderFactory.newInstance();

    protected abstract void load();

    protected abstract void parseDocument(Document var1, File var2);

    public void loadDocument(String filePath) {
        this.loadDocument(new File(filePath));
    }

    public void loadDocument(File file) {
        block5: {
            block4: {
                if (!file.exists()) {
                    LOGGER.warning("The following file or directory doesn't exist: {}.");
                    return;
                }
                if (!file.isDirectory()) break block4;
                for (File f : file.listFiles()) {
                    this.loadDocument(f);
                }
                break block5;
            }
            if (!file.isFile()) break block5;
            try {
                this.parseDocument(BUILDER.newDocumentBuilder().parse(file), file);
            }
            catch (Exception e) {
                LOGGER.warning("Error loading XML file '{}'.");
            }
        }
    }

    public static void parseAndFeed(NamedNodeMap attrs, StatsSet set) {
        for (int i = 0; i < attrs.getLength(); ++i) {
            Node attr = attrs.item(i);
            set.set(attr.getNodeName(), attr.getNodeValue());
        }
    }

    public void forEach(Node node, Consumer<Node> action) {
        this.forEach(node, (Node a) -> true, action);
    }

    public void forEach(Node node, String nodeName, Consumer<Node> action) {
        this.forEach(node, (Node innerNode) -> {
            if (nodeName.contains("|")) {
                String[] nodeNames = nodeName.split("\\|");
                for (String name : nodeNames) {
                    if (name.isEmpty() || !name.equals(innerNode.getNodeName())) continue;
                    return true;
                }
                return false;
            }
            return nodeName.equals(innerNode.getNodeName());
        }, action);
    }

    public void forEach(Node node, Predicate<Node> filter, Consumer<Node> action) {
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); ++i) {
            Node targetNode = list.item(i);
            if (!filter.test(targetNode)) continue;
            action.accept(targetNode);
        }
    }

    public StatsSet parseAttributes(Node node) {
        NamedNodeMap attrs = node.getAttributes();
        StatsSet map = new StatsSet();
        for (int i = 0; i < attrs.getLength(); ++i) {
            Node att = attrs.item(i);
            map.put(att.getNodeName(), att.getNodeValue());
        }
        return map;
    }

    static {
        BUILDER.setValidating(false);
        BUILDER.setIgnoringComments(true);
    }
}


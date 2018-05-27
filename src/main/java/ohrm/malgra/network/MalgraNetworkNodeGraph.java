package ohrm.malgra.network;

import com.google.common.collect.Sets;
import ohrm.malgra.api.network.IMalgraNetwork;
import ohrm.malgra.api.network.IMalgraNetworkNode;
import ohrm.malgra.api.network.IMalgraNetworkNodeGraph;

import java.util.Collection;
import java.util.Set;

public class MalgraNetworkNodeGraph implements IMalgraNetworkNodeGraph {

    private Set<IMalgraNetworkNode> nodes = Sets.newConcurrentHashSet();
    private IMalgraNetwork network;

    public MalgraNetworkNodeGraph(IMalgraNetwork controller){
        network = controller;
    }

    @Override
    public Collection<IMalgraNetworkNode> getAllNodes() {
        return nodes;
    }

    @Override
    public void removeAllNodes() {
        nodes.forEach(n -> {n.notifyRemove(); n.onDisconnected(network);});
        nodes.clear();
    }

    @Override
    public void addNode(IMalgraNetworkNode node) {
        nodes.add(node);
    }

    @Override
    public void removeNode(IMalgraNetworkNode node) {
        nodes.remove(node);
    }
}

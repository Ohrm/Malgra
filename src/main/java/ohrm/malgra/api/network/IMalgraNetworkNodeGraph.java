package ohrm.malgra.api.network;

import java.util.Collection;

public interface IMalgraNetworkNodeGraph {

    Collection<IMalgraNetworkNode> getAllNodes();

    void removeAllNodes();

    void addNode(IMalgraNetworkNode node);

    void removeNode(IMalgraNetworkNode node);

}

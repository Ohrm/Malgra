package ohrm.malgra.api.network;

public interface IMalgraLinkable {
    void onLinkedSource(IMalgraLinkable linkedTo);
    void onLinkedDestination(IMalgraLinkable linkedTo);
}

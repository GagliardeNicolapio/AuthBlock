package com.example.authblock.chain;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Int;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;

import java.util.Arrays;

public class ListenerPagamentoEffettuato {
    public static final Event PAGAMENTO_EFFETTUATO_EVENT = new Event("PagamentoRicevuto",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Int>(false) {}));
    private static final String INCREMENT_EVENT_HASH = EventEncoder.encode(PAGAMENTO_EFFETTUATO_EVENT);
    private AuthBlockChain authBlockChain = new AuthBlockChain();
    @EventListener(ApplicationReadyEvent.class)
    public void listener() {

        /*EthFilter filter = new EthFilter(
                DefaultBlockParameterName.EARLIEST, // From block 0
                DefaultBlockParameterName.LATEST,  // To latest
                "0x05eC8011d7B54129e0FeD376c4Cff4A91D9D2f15") // Unique Smart Contract
                .addSingleTopic(INCREMENT_EVENT_HASH);

        web3.ethLogFlowable(filter).subscribe(event -> {
            event.getAddress(); // Smart contract address
            event.getBlockNumber(); // Block number
            event.getTransactionHash(); // Transaction that emitted the event
            event.getTopics().get(0); // Event hash
            event.getTopics().get(1-n); // Event parameter (1) _by, (2) _value
        });*/

        EthFilter filter = new EthFilter(
                DefaultBlockParameterName.EARLIEST, // From block 0
                DefaultBlockParameterName.LATEST,  // To latest
                "0x6677a85BDeCf4b2c32266c23c6c16Ad4AB321d90") // Unique Smart Contract
                .addSingleTopic(INCREMENT_EVENT_HASH);

        authBlockChain.contract.pagamentoRicevutoEventFlowable(filter).subscribe(event ->{
            System.out.println("TEST EVENT");
            System.out.println(event.toString());
        });
    }
}

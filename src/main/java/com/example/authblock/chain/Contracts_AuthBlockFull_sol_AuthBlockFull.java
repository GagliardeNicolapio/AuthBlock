package com.example.authblock.chain;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.2.
 */
@SuppressWarnings("rawtypes")
public class Contracts_AuthBlockFull_sol_AuthBlockFull extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5061001a3361001f565b61006f565b600080546001600160a01b038381166001600160a01b0319831681178455604051919092169283917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e09190a35050565b61154a8061007e6000396000f3fe6080604052600436106100ab5760003560e01c80638da5cb5b116100645780638da5cb5b1461020c5780639a133fae14610234578063b023ff5b14610261578063bf0e054a146102a5578063ce0f731a146102db578063f2fde38b146102fb57600080fd5b80633575a1ff14610140578063373cf2021461016257806350f1863314610197578063715018a6146101b7578063715fbd8f146101cc578063761bdc1e146101ec57600080fd5b3661013b57670de0b6b3a76400003410156101065760405162461bcd60e51b81526020600482015260166024820152752134b9b7b3b730903830b3b0b93290189032ba3432b960511b60448201526064015b60405180910390fd5b6040513381527fb82feae9da1971f4db0fe644ffdcd97a911db5ce2ca18420226c08831a30e3f09060200160405180910390a1005b600080fd5b34801561014c57600080fd5b5061016061015b366004610f1e565b61031b565b005b34801561016e57600080fd5b5061018261017d366004610f60565b610491565b60405190151581526020015b60405180910390f35b3480156101a357600080fd5b506101606101b23660046110cf565b6104d2565b3480156101c357600080fd5b506101606107a8565b3480156101d857600080fd5b506101606101e73660046110cf565b6107bc565b3480156101f857600080fd5b50610182610207366004611154565b610887565b34801561021857600080fd5b506000546040516001600160a01b03909116815260200161018e565b34801561024057600080fd5b5061025461024f366004611176565b6108c6565b60405161018e91906111c4565b34801561026d57600080fd5b5061029761027c366004611154565b6001600160a01b031660009081526003602052604090205490565b60405190815260200161018e565b3480156102b157600080fd5b506102976102c0366004611154565b6001600160a01b031660009081526001602052604090205490565b3480156102e757600080fd5b506102546102f6366004611176565b610af0565b34801561030757600080fd5b50610160610316366004611154565b610bee565b610323610c67565b6001600160a01b038416600090815260016020526040902054821061034757600080fd5b6001600160a01b038316600090815260036020526040902054811061036b57600080fd5b6001600160a01b0383166000908152600360205260409020805482908110610395576103956111f7565b9060005260206000209060030201600201546000146103b357600080fd5b6001600160a01b03841660009081526001602052604090208054839081106103dd576103dd6111f7565b9060005260206000209060050201600101546000146103fb57600080fd5b6001600160a01b038416600090815260016020526040902080544291829185908110610429576104296111f7565b9060005260206000209060050201600101819055508060036000866001600160a01b03166001600160a01b031681526020019081526020016000208381548110610475576104756111f7565b9060005260206000209060030201600201819055505050505050565b6001600160a01b038083166000908152600260209081526040808320938516835292905290812080548291906104c69061120d565b90501190505b92915050565b6104da610c67565b6040805160a08101825242808252600060208084018290526001600160a01b03808a168352600282528583209089168352905283812080549294919392918301916105249061120d565b80601f01602080910402602001604051908101604052809291908181526020018280546105509061120d565b801561059d5780601f106105725761010080835404028352916020019161059d565b820191906000526020600020905b81548152906001019060200180831161058057829003601f168201915b50505050508152602001856001815181106105ba576105ba6111f7565b60200260200101518152602001856002815181106105da576105da6111f7565b602002602001015181525090506000604051806060016040528085600081518110610607576106076111f7565b6020908102919091018101518252818101869052600060409283018190526001600160a01b038b168152600180835283822080548083018255908352918390208751600590930201918255918601519181019190915590840151919250839160028201906106759082611296565b506060820151600382019061068a9082611296565b506080820151600482019061069f9082611296565b5050506001600160a01b038616600090815260036020818152604083208054600181018255908452922083518493909202019081906106de9082611296565b50602082015181600101556040820151816002015550507ff158013eb12ed8b7af55bab269a0776b618bb20341f45c634b93991a09554048878760018060008c6001600160a01b03166001600160a01b031681526020019081526020016000208054905061074c9190611356565b6001600160a01b038a1660009081526003602052604090205461077190600190611356565b604080516001600160a01b03958616815294909316602085015291830152606082015260800160405180910390a150505050505050565b6107b0610c67565b6107ba6000610cc1565b565b6107c4610c67565b6000826002815181106107d9576107d96111f7565b602002602001015151116108255760405162461bcd60e51b8152602060048201526013602482015272757365726e616d65206e6f6e2076616c69646160681b60448201526064016100fd565b81600081518110610838576108386111f7565b6020908102919091018101516001600160a01b038087166000908152600284526040808220928816825291909352909120906108749082611296565b50610881848484846104d2565b50505050565b6001600160a01b0381166000908152600160205260408120541515806104cc5750506001600160a01b0316600090815260036020526040902054151590565b60606108d0610c67565b6001600160a01b03831660009081526001602052604081208054849081106108fa576108fa6111f7565b90600052602060002090600502016040518060a001604052908160008201548152602001600182015481526020016002820180546109379061120d565b80601f01602080910402602001604051908101604052809291908181526020018280546109639061120d565b80156109b05780601f10610985576101008083540402835291602001916109b0565b820191906000526020600020905b81548152906001019060200180831161099357829003601f168201915b505050505081526020016003820180546109c99061120d565b80601f01602080910402602001604051908101604052809291908181526020018280546109f59061120d565b8015610a425780601f10610a1757610100808354040283529160200191610a42565b820191906000526020600020905b815481529060010190602001808311610a2557829003601f168201915b50505050508152602001600482018054610a5b9061120d565b80601f0160208091040260200160405190810160405280929190818152602001828054610a879061120d565b8015610ad45780601f10610aa957610100808354040283529160200191610ad4565b820191906000526020600020905b815481529060010190602001808311610ab757829003601f168201915b5050505050815250509050610ae881610d11565b949350505050565b6060610afa610c67565b6001600160a01b0383166000908152600360205260408120805484908110610b2457610b246111f7565b9060005260206000209060030201604051806060016040529081600082018054610b4d9061120d565b80601f0160208091040260200160405190810160405280929190818152602001828054610b799061120d565b8015610bc65780601f10610b9b57610100808354040283529160200191610bc6565b820191906000526020600020905b815481529060010190602001808311610ba957829003601f168201915b50505050508152602001600182015481526020016002820154815250509050610ae881610d64565b610bf6610c67565b6001600160a01b038116610c5b5760405162461bcd60e51b815260206004820152602660248201527f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206160448201526564647265737360d01b60648201526084016100fd565b610c6481610cc1565b50565b6000546001600160a01b031633146107ba5760405162461bcd60e51b815260206004820181905260248201527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e657260448201526064016100fd565b600080546001600160a01b038381166001600160a01b0319831681178455604051919092169283917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e09190a35050565b6060610d208260000151610d97565b610d2d8360200151610d97565b604080850151606086015160808701519251610d4e95949390602001611377565b6040516020818303038152906040529050919050565b60608160000151610d788360200151610d97565b610d858460400151610d97565b604051602001610d4e93929190611478565b60606000610da483610e2a565b600101905060008167ffffffffffffffff811115610dc457610dc4610f93565b6040519080825280601f01601f191660200182016040528015610dee576020820181803683370190505b5090508181016020015b600019016f181899199a1a9b1b9c1cb0b131b232b360811b600a86061a8153600a8504945084610df857509392505050565b60008072184f03e93ff9f4daa797ed6e38ed64bf6a1f0160401b8310610e695772184f03e93ff9f4daa797ed6e38ed64bf6a1f0160401b830492506040015b6d04ee2d6d415b85acef81000000008310610e95576d04ee2d6d415b85acef8100000000830492506020015b662386f26fc100008310610eb357662386f26fc10000830492506010015b6305f5e1008310610ecb576305f5e100830492506008015b6127108310610edf57612710830492506004015b60648310610ef1576064830492506002015b600a83106104cc5760010192915050565b80356001600160a01b0381168114610f1957600080fd5b919050565b60008060008060808587031215610f3457600080fd5b610f3d85610f02565b9350610f4b60208601610f02565b93969395505050506040820135916060013590565b60008060408385031215610f7357600080fd5b610f7c83610f02565b9150610f8a60208401610f02565b90509250929050565b634e487b7160e01b600052604160045260246000fd5b604051601f8201601f1916810167ffffffffffffffff81118282101715610fd257610fd2610f93565b604052919050565b6000601f8381840112610fec57600080fd5b8235602067ffffffffffffffff8083111561100957611009610f93565b8260051b611018838201610fa9565b938452868101830193838101908986111561103257600080fd5b84890192505b858310156110c2578235848111156110505760008081fd5b8901603f81018b136110625760008081fd5b8581013560408682111561107857611078610f93565b611089828b01601f19168901610fa9565b8281528d8284860101111561109e5760008081fd5b828285018a8301376000928101890192909252508352509184019190840190611038565b9998505050505050505050565b600080600080608085870312156110e557600080fd5b6110ee85610f02565b93506110fc60208601610f02565b9250604085013567ffffffffffffffff8082111561111957600080fd5b61112588838901610fda565b9350606087013591508082111561113b57600080fd5b5061114887828801610fda565b91505092959194509250565b60006020828403121561116657600080fd5b61116f82610f02565b9392505050565b6000806040838503121561118957600080fd5b61119283610f02565b946020939093013593505050565b60005b838110156111bb5781810151838201526020016111a3565b50506000910152565b60208152600082518060208401526111e38160408501602087016111a0565b601f01601f19169190910160400192915050565b634e487b7160e01b600052603260045260246000fd5b600181811c9082168061122157607f821691505b60208210810361124157634e487b7160e01b600052602260045260246000fd5b50919050565b601f82111561129157600081815260208120601f850160051c8101602086101561126e5750805b601f850160051c820191505b8181101561128d5782815560010161127a565b5050505b505050565b815167ffffffffffffffff8111156112b0576112b0610f93565b6112c4816112be845461120d565b84611247565b602080601f8311600181146112f957600084156112e15750858301515b600019600386901b1c1916600185901b17855561128d565b600085815260208120601f198616915b8281101561132857888601518255948401946001909101908401611309565b50858210156113465787850151600019600388901b60f8161c191681555b5050505050600190811b01905550565b818103818111156104cc57634e487b7160e01b600052601160045260246000fd5b6a3db7b930a637b3b4b71d1160a91b8152855160009061139e81600b850160208b016111a0565b6d11161037b930a637b3b7baba1d1160911b600b9184019182015286516113cc816019840160208b016111a0565b6c1116103ab9b2b93730b6b29d1160991b6019929091019182015285516113fa816026840160208a016111a0565b6d1116103ab9b2b920b3b2b73a1d1160911b6026929091019182015284516114298160348401602089016111a0565b6d11161034b820b2323932b9b99d1160911b6034929091019182015283516114588160428401602088016111a0565b0161146a6042820161227d60f01b9052565b604401979650505050505050565b693dbab93629b4ba379d1160b11b8152835160009061149e81600a8501602089016111a0565b6b111637b930a637b3b4b71d1160a11b600a9184019182015284516114ca8160168401602089016111a0565b6c111637b930a637b3b7baba1d1160991b6016929091019182015283516114f88160238401602088016111a0565b61227d60f01b602392909101918201526025019594505050505056fea264697066735822122030f29e0401c642e2768ffd95f0bbccb35c995ce883eb5cf77383aa358b3d826d64736f6c63430008120033";

    public static final String FUNC_CHECKUSER = "checkUser";

    public static final String FUNC_GETINFOACCESSOSITO = "getInfoAccessoSito";

    public static final String FUNC_GETINFOACCESSOUTENTE = "getInfoAccessoUtente";

    public static final String FUNC_GETNUMBERACCESSISITO = "getNumberAccessiSito";

    public static final String FUNC_GETNUMBERACCESSIUTENTE = "getNumberAccessiUtente";

    public static final String FUNC_INSERTACCESSO = "insertAccesso";

    public static final String FUNC_INSERTLOGOUT = "insertLogout";

    public static final String FUNC_INSERTUSER = "insertUser";

    public static final String FUNC_ISPRESENT = "isPresent";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final Event INSERIMENTOACCESSOFATTO_EVENT = new Event("InserimentoAccessoFatto", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event PAGAMENTORICEVUTO_EVENT = new Event("PagamentoRicevuto", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    @Deprecated
    protected Contracts_AuthBlockFull_sol_AuthBlockFull(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Contracts_AuthBlockFull_sol_AuthBlockFull(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Contracts_AuthBlockFull_sol_AuthBlockFull(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Contracts_AuthBlockFull_sol_AuthBlockFull(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static InserimentoAccessoFattoEventResponse getInserimentoAccessoFattoEvents(Log log) {
        Contract.EventValuesWithLog valueList = staticExtractEventParametersWithLog(INSERIMENTOACCESSOFATTO_EVENT, log);
        //ArrayList<InserimentoAccessoFattoEventResponse> responses = new ArrayList<InserimentoAccessoFattoEventResponse>(valueList.size());
        //for (Contract.EventValuesWithLog eventValues : valueList) {
        InserimentoAccessoFattoEventResponse typedResponse = new InserimentoAccessoFattoEventResponse();
        typedResponse.log = valueList.getLog();
        typedResponse.indirizzoSito = (String) valueList.getNonIndexedValues().get(0).getValue();
        System.out.println(typedResponse.indirizzoSito);
        typedResponse.indirizzoUtente = (String) valueList.getNonIndexedValues().get(1).getValue();
        System.out.println( typedResponse.indirizzoUtente);
        typedResponse.idAccessoSito = (BigInteger) valueList.getNonIndexedValues().get(2).getValue();
        System.out.println(typedResponse.idAccessoSito);
        typedResponse.idAccessoUtente = (BigInteger) valueList.getNonIndexedValues().get(3).getValue();
        System.out.println(   typedResponse.idAccessoUtente);
        //responses.add(typedResponse);
        // }
        return typedResponse;
    }



    public Flowable<InserimentoAccessoFattoEventResponse> inserimentoAccessoFattoEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, InserimentoAccessoFattoEventResponse>() {
            @Override
            public InserimentoAccessoFattoEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(INSERIMENTOACCESSOFATTO_EVENT, log);
                InserimentoAccessoFattoEventResponse typedResponse = new InserimentoAccessoFattoEventResponse();
                typedResponse.log = log;
                typedResponse.indirizzoSito = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.indirizzoUtente = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.idAccessoSito = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.idAccessoUtente = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<InserimentoAccessoFattoEventResponse> inserimentoAccessoFattoEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(INSERIMENTOACCESSOFATTO_EVENT));
        return inserimentoAccessoFattoEventFlowable(filter);
    }

    /*public static List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }*/

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
                OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
                typedResponse.log = log;
                typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    /*public static List<PagamentoRicevutoEventResponse> getPagamentoRicevutoEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(PAGAMENTORICEVUTO_EVENT, transactionReceipt);
        ArrayList<PagamentoRicevutoEventResponse> responses = new ArrayList<PagamentoRicevutoEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PagamentoRicevutoEventResponse typedResponse = new PagamentoRicevutoEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.indirizzoSito = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }*/

    public Flowable<PagamentoRicevutoEventResponse> pagamentoRicevutoEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, PagamentoRicevutoEventResponse>() {
            @Override
            public PagamentoRicevutoEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(PAGAMENTORICEVUTO_EVENT, log);
                PagamentoRicevutoEventResponse typedResponse = new PagamentoRicevutoEventResponse();
                typedResponse.log = log;
                typedResponse.indirizzoSito = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<PagamentoRicevutoEventResponse> pagamentoRicevutoEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PAGAMENTORICEVUTO_EVENT));
        return pagamentoRicevutoEventFlowable(filter);
    }

    public RemoteFunctionCall<Boolean> checkUser(String _indirizzoSito, String _indirizzoUtente) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_CHECKUSER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _indirizzoSito), 
                new org.web3j.abi.datatypes.Address(160, _indirizzoUtente)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> getInfoAccessoSito(String indirizzoSito, BigInteger accessNumber) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETINFOACCESSOSITO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, indirizzoSito), 
                new org.web3j.abi.datatypes.generated.Uint256(accessNumber)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getInfoAccessoUtente(String indirizzoUtente, BigInteger accessNumber) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETINFOACCESSOUTENTE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, indirizzoUtente), 
                new org.web3j.abi.datatypes.generated.Uint256(accessNumber)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getNumberAccessiSito(String indirizzoSito) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETNUMBERACCESSISITO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, indirizzoSito)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getNumberAccessiUtente(String indirizzoUtente) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETNUMBERACCESSIUTENTE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, indirizzoUtente)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> insertAccesso(String _indirizzoSito, String _indirizzoUtente, List<String> _dataInfoAccessoSito, List<String> _dataInfoAccessoUtente) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INSERTACCESSO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _indirizzoSito), 
                new org.web3j.abi.datatypes.Address(160, _indirizzoUtente), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Utf8String>(
                        org.web3j.abi.datatypes.Utf8String.class,
                        org.web3j.abi.Utils.typeMap(_dataInfoAccessoSito, org.web3j.abi.datatypes.Utf8String.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Utf8String>(
                        org.web3j.abi.datatypes.Utf8String.class,
                        org.web3j.abi.Utils.typeMap(_dataInfoAccessoUtente, org.web3j.abi.datatypes.Utf8String.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> insertLogout(String _indirizzoSito, String _indirizzoUtente, BigInteger idInfoSito, BigInteger idInfoUtente) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INSERTLOGOUT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _indirizzoSito), 
                new org.web3j.abi.datatypes.Address(160, _indirizzoUtente), 
                new org.web3j.abi.datatypes.generated.Uint256(idInfoSito), 
                new org.web3j.abi.datatypes.generated.Uint256(idInfoUtente)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> insertUser(String _indirizzoSito, String _indirizzoUtente, List<String> _dataInfoAccessoSito, List<String> _dataInfoAccessoUtente) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INSERTUSER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _indirizzoSito), 
                new org.web3j.abi.datatypes.Address(160, _indirizzoUtente), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Utf8String>(
                        org.web3j.abi.datatypes.Utf8String.class,
                        org.web3j.abi.Utils.typeMap(_dataInfoAccessoSito, org.web3j.abi.datatypes.Utf8String.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Utf8String>(
                        org.web3j.abi.datatypes.Utf8String.class,
                        org.web3j.abi.Utils.typeMap(_dataInfoAccessoUtente, org.web3j.abi.datatypes.Utf8String.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> isPresent(String indirizzo) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISPRESENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, indirizzo)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Contracts_AuthBlockFull_sol_AuthBlockFull load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Contracts_AuthBlockFull_sol_AuthBlockFull(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Contracts_AuthBlockFull_sol_AuthBlockFull load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Contracts_AuthBlockFull_sol_AuthBlockFull(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Contracts_AuthBlockFull_sol_AuthBlockFull load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Contracts_AuthBlockFull_sol_AuthBlockFull(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Contracts_AuthBlockFull_sol_AuthBlockFull load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Contracts_AuthBlockFull_sol_AuthBlockFull(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Contracts_AuthBlockFull_sol_AuthBlockFull> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Contracts_AuthBlockFull_sol_AuthBlockFull.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Contracts_AuthBlockFull_sol_AuthBlockFull> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Contracts_AuthBlockFull_sol_AuthBlockFull.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Contracts_AuthBlockFull_sol_AuthBlockFull> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Contracts_AuthBlockFull_sol_AuthBlockFull.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Contracts_AuthBlockFull_sol_AuthBlockFull> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Contracts_AuthBlockFull_sol_AuthBlockFull.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class InserimentoAccessoFattoEventResponse extends BaseEventResponse {
        public String indirizzoSito;

        public String indirizzoUtente;

        public BigInteger idAccessoSito;

        public BigInteger idAccessoUtente;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class PagamentoRicevutoEventResponse extends BaseEventResponse {
        public String indirizzoSito;
    }
}

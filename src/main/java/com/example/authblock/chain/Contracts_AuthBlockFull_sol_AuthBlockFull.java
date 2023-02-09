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
    public static final String BINARY = "608060405234801561001057600080fd5b5061001a3361001f565b61006f565b600080546001600160a01b038381166001600160a01b0319831681178455604051919092169283917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e09190a35050565b6119a88061007e6000396000f3fe608060405234801561001057600080fd5b50600436106100cf5760003560e01c8063715fbd8f1161008c5780639a133fae116100665780639a133fae146101a4578063b023ff5b146101b7578063bf0e054a146101ee578063f2fde38b1461021757600080fd5b8063715fbd8f14610163578063761bdc1e146101765780638da5cb5b1461018957600080fd5b806332ea715f146100d45780633575a1ff146100fd578063373cf2021461011257806350f1863314610135578063557c3d5a14610148578063715018a61461015b575b600080fd5b6100e76100e236600461128b565b61022a565b6040516100f491906112e2565b60405180910390f35b61011061010b366004611315565b6105cd565b005b610125610120366004611357565b6106b3565b60405190151581526020016100f4565b6101106101433660046114c6565b6106f4565b6100e761015636600461128b565b6109ca565b610110610c19565b6101106101713660046114c6565b610c2d565b61012561018436600461154b565b610cf2565b6000546040516001600160a01b0390911681526020016100f4565b6100e76101b236600461156d565b610d31565b6101e06101c536600461154b565b6001600160a01b031660009081526003602052604090205490565b6040519081526020016100f4565b6101e06101fc36600461154b565b6001600160a01b031660009081526001602052604090205490565b61011061022536600461154b565b610f5b565b6060610234610fd4565b8183106102885760405162461bcd60e51b815260206004820152601a60248201527f7374617274206e6f6e206527206d696e6f726520646920656e6400000000000060448201526064015b60405180910390fd5b6001600160a01b0384166000908152600160205260409020548211156102ec5760405162461bcd60e51b8152602060048201526019602482015278195b99081949c81b5859d9da5bdc9948191a481b195b99da1d603a1b604482015260640161027f565b6001600160a01b0384166000908152600160205260408120805461050391908690811061031b5761031b611597565b90600052602060002090600502016040518060a00160405290816000820154815260200160018201548152602001600282018054610358906115ad565b80601f0160208091040260200160405190810160405280929190818152602001828054610384906115ad565b80156103d15780601f106103a6576101008083540402835291602001916103d1565b820191906000526020600020905b8154815290600101906020018083116103b457829003601f168201915b505050505081526020016003820180546103ea906115ad565b80601f0160208091040260200160405190810160405280929190818152602001828054610416906115ad565b80156104635780601f1061043857610100808354040283529160200191610463565b820191906000526020600020905b81548152906001019060200180831161044657829003601f168201915b5050505050815260200160048201805461047c906115ad565b80601f01602080910402602001604051908101604052809291908181526020018280546104a8906115ad565b80156104f55780601f106104ca576101008083540402835291602001916104f5565b820191906000526020600020905b8154815290600101906020018083116104d857829003601f168201915b50505050508152505061102e565b60405160200161051391906115e7565b60408051601f1981840301815291905290506000610532856001611626565b90505b838110156105a2576001600160a01b03861660009081526001602052604090208054839161056d918490811061031b5761031b611597565b60405160200161057e929190611639565b6040516020818303038152906040529150808061059a90611675565b915050610535565b50806040516020016105b4919061168e565b60408051808303601f1901815291905295945050505050565b6105d5610fd4565b6001600160a01b03841660009081526001602052604090205482106105f957600080fd5b6001600160a01b038316600090815260036020526040902054811061061d57600080fd5b6001600160a01b038316600090815260036020526040902080548290811061064757610647611597565b90600052602060002090600302016002015460001461066557600080fd5b6001600160a01b038416600090815260016020526040902080548390811061068f5761068f611597565b9060005260206000209060050201600101546000146106ad57600080fd5b50505050565b6001600160a01b038083166000908152600260209081526040808320938516835292905290812080548291906106e8906115ad565b90501190505b92915050565b6106fc610fd4565b6040805160a08101825242808252600060208084018290526001600160a01b03808a16835260028252858320908916835290528381208054929491939291830191610746906115ad565b80601f0160208091040260200160405190810160405280929190818152602001828054610772906115ad565b80156107bf5780601f10610794576101008083540402835291602001916107bf565b820191906000526020600020905b8154815290600101906020018083116107a257829003601f168201915b50505050508152602001856001815181106107dc576107dc611597565b60200260200101518152602001856002815181106107fc576107fc611597565b60200260200101518152509050600060405180606001604052808560008151811061082957610829611597565b6020908102919091018101518252818101869052600060409283018190526001600160a01b038b168152600180835283822080548083018255908352918390208751600590930201918255918601519181019190915590840151919250839160028201906108979082611702565b50606082015160038201906108ac9082611702565b50608082015160048201906108c19082611702565b5050506001600160a01b038616600090815260036020818152604083208054600181018255908452922083518493909202019081906109009082611702565b50602082015181600101556040820151816002015550507ff158013eb12ed8b7af55bab269a0776b618bb20341f45c634b93991a09554048878760018060008c6001600160a01b03166001600160a01b031681526020019081526020016000208054905061096e91906117c2565b6001600160a01b038a16600090815260036020526040902054610993906001906117c2565b604080516001600160a01b03958616815294909316602085015291830152606082015260800160405180910390a150505050505050565b60606109d4610fd4565b818310610a235760405162461bcd60e51b815260206004820152601a60248201527f7374617274206e6f6e206527206d696e6f726520646920656e64000000000000604482015260640161027f565b6001600160a01b038416600090815260036020526040902054821115610a875760405162461bcd60e51b8152602060048201526019602482015278195b99081949c81b5859d9da5bdc9948191a481b195b99da1d603a1b604482015260640161027f565b6001600160a01b03841660009081526003602052604081208054610b7a919086908110610ab657610ab6611597565b9060005260206000209060030201604051806060016040529081600082018054610adf906115ad565b80601f0160208091040260200160405190810160405280929190818152602001828054610b0b906115ad565b8015610b585780601f10610b2d57610100808354040283529160200191610b58565b820191906000526020600020905b815481529060010190602001808311610b3b57829003601f168201915b5050505050815260200160018201548152602001600282015481525050611081565b604051602001610b8a91906115e7565b60408051601f1981840301815291905290506000610ba9856001611626565b90505b838110156105a2576001600160a01b038616600090815260036020526040902080548391610be49184908110610ab657610ab6611597565b604051602001610bf5929190611639565b60405160208183030381529060405291508080610c1190611675565b915050610bac565b610c21610fd4565b610c2b60006110b4565b565b610c35610fd4565b600082600281518110610c4a57610c4a611597565b60200260200101515111610c965760405162461bcd60e51b8152602060048201526013602482015272757365726e616d65206e6f6e2076616c69646160681b604482015260640161027f565b81600281518110610ca957610ca9611597565b6020908102919091018101516001600160a01b03808716600090815260028452604080822092881682529190935290912090610ce59082611702565b506106ad848484846106f4565b6001600160a01b0381166000908152600160205260408120541515806106ee5750506001600160a01b0316600090815260036020526040902054151590565b6060610d3b610fd4565b6001600160a01b0383166000908152600160205260408120805484908110610d6557610d65611597565b90600052602060002090600502016040518060a00160405290816000820154815260200160018201548152602001600282018054610da2906115ad565b80601f0160208091040260200160405190810160405280929190818152602001828054610dce906115ad565b8015610e1b5780601f10610df057610100808354040283529160200191610e1b565b820191906000526020600020905b815481529060010190602001808311610dfe57829003601f168201915b50505050508152602001600382018054610e34906115ad565b80601f0160208091040260200160405190810160405280929190818152602001828054610e60906115ad565b8015610ead5780601f10610e8257610100808354040283529160200191610ead565b820191906000526020600020905b815481529060010190602001808311610e9057829003601f168201915b50505050508152602001600482018054610ec6906115ad565b80601f0160208091040260200160405190810160405280929190818152602001828054610ef2906115ad565b8015610f3f5780601f10610f1457610100808354040283529160200191610f3f565b820191906000526020600020905b815481529060010190602001808311610f2257829003601f168201915b5050505050815250509050610f538161102e565b949350505050565b610f63610fd4565b6001600160a01b038116610fc85760405162461bcd60e51b815260206004820152602660248201527f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206160448201526564647265737360d01b606482015260840161027f565b610fd1816110b4565b50565b6000546001600160a01b03163314610c2b5760405162461bcd60e51b815260206004820181905260248201527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e6572604482015260640161027f565b606061103d8260000151611104565b61104a8360200151611104565b60408085015160608601516080870151925161106b959493906020016117d5565b6040516020818303038152906040529050919050565b606081600001516110958360200151611104565b6110a28460400151611104565b60405160200161106b939291906118d6565b600080546001600160a01b038381166001600160a01b0319831681178455604051919092169283917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e09190a35050565b6060600061111183611197565b600101905060008167ffffffffffffffff8111156111315761113161138a565b6040519080825280601f01601f19166020018201604052801561115b576020820181803683370190505b5090508181016020015b600019016f181899199a1a9b1b9c1cb0b131b232b360811b600a86061a8153600a850494508461116557509392505050565b60008072184f03e93ff9f4daa797ed6e38ed64bf6a1f0160401b83106111d65772184f03e93ff9f4daa797ed6e38ed64bf6a1f0160401b830492506040015b6d04ee2d6d415b85acef81000000008310611202576d04ee2d6d415b85acef8100000000830492506020015b662386f26fc10000831061122057662386f26fc10000830492506010015b6305f5e1008310611238576305f5e100830492506008015b612710831061124c57612710830492506004015b6064831061125e576064830492506002015b600a83106106ee5760010192915050565b80356001600160a01b038116811461128657600080fd5b919050565b6000806000606084860312156112a057600080fd5b6112a98461126f565b95602085013595506040909401359392505050565b60005b838110156112d95781810151838201526020016112c1565b50506000910152565b60208152600082518060208401526113018160408501602087016112be565b601f01601f19169190910160400192915050565b6000806000806080858703121561132b57600080fd5b6113348561126f565b93506113426020860161126f565b93969395505050506040820135916060013590565b6000806040838503121561136a57600080fd5b6113738361126f565b91506113816020840161126f565b90509250929050565b634e487b7160e01b600052604160045260246000fd5b604051601f8201601f1916810167ffffffffffffffff811182821017156113c9576113c961138a565b604052919050565b6000601f83818401126113e357600080fd5b8235602067ffffffffffffffff808311156114005761140061138a565b8260051b61140f8382016113a0565b938452868101830193838101908986111561142957600080fd5b84890192505b858310156114b9578235848111156114475760008081fd5b8901603f81018b136114595760008081fd5b8581013560408682111561146f5761146f61138a565b611480828b01601f191689016113a0565b8281528d828486010111156114955760008081fd5b828285018a830137600092810189019290925250835250918401919084019061142f565b9998505050505050505050565b600080600080608085870312156114dc57600080fd5b6114e58561126f565b93506114f36020860161126f565b9250604085013567ffffffffffffffff8082111561151057600080fd5b61151c888389016113d1565b9350606087013591508082111561153257600080fd5b5061153f878288016113d1565b91505092959194509250565b60006020828403121561155d57600080fd5b6115668261126f565b9392505050565b6000806040838503121561158057600080fd5b6115898361126f565b946020939093013593505050565b634e487b7160e01b600052603260045260246000fd5b600181811c908216806115c157607f821691505b6020821081036115e157634e487b7160e01b600052602260045260246000fd5b50919050565b605b60f81b8152600082516116038160018501602087016112be565b9190910160010192915050565b634e487b7160e01b600052601160045260246000fd5b808201808211156106ee576106ee611610565b6000835161164b8184602088016112be565b600b60fa1b90830190815283516116698160018401602088016112be565b01600101949350505050565b60006001820161168757611687611610565b5060010190565b600082516116a08184602087016112be565b605d60f81b920191825250600101919050565b601f8211156116fd57600081815260208120601f850160051c810160208610156116da5750805b601f850160051c820191505b818110156116f9578281556001016116e6565b5050505b505050565b815167ffffffffffffffff81111561171c5761171c61138a565b6117308161172a84546115ad565b846116b3565b602080601f831160018114611765576000841561174d5750858301515b600019600386901b1c1916600185901b1785556116f9565b600085815260208120601f198616915b8281101561179457888601518255948401946001909101908401611775565b50858210156117b25787850151600019600388901b60f8161c191681555b5050505050600190811b01905550565b818103818111156106ee576106ee611610565b6a3db7b930a637b3b4b71d1160a91b815285516000906117fc81600b850160208b016112be565b6d11161037b930a637b3b7baba1d1160911b600b91840191820152865161182a816019840160208b016112be565b6c1116103ab9b2b93730b6b29d1160991b601992909101918201528551611858816026840160208a016112be565b6d1116103ab9b2b920b3b2b73a1d1160911b6026929091019182015284516118878160348401602089016112be565b6d11161034b820b2323932b9b99d1160911b6034929091019182015283516118b68160428401602088016112be565b016118c86042820161227d60f01b9052565b604401979650505050505050565b693dbab93629b4ba379d1160b11b815283516000906118fc81600a8501602089016112be565b6b111637b930a637b3b4b71d1160a11b600a9184019182015284516119288160168401602089016112be565b6c111637b930a637b3b7baba1d1160991b6016929091019182015283516119568160238401602088016112be565b61227d60f01b602392909101918201526025019594505050505056fea264697066735822122005997466e7a6563236fbbbbb46963e39d7e90e911ebbf0cfad3253c849e6667564736f6c63430008120033";

    public static final String FUNC_CHECKUSER = "checkUser";

    public static final String FUNC_GETINFOACCESSOSITO = "getInfoAccessoSito";

    public static final String FUNC_GETINFOACCESSOSITORANGE = "getInfoAccessoSitoRange";

    public static final String FUNC_GETINFOACCESSOUTENTIRANGE = "getInfoAccessoUtentiRange";

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

    public RemoteFunctionCall<String> getInfoAccessoSitoRange(String indirizzoSito, BigInteger start, BigInteger end) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETINFOACCESSOSITORANGE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, indirizzoSito), 
                new org.web3j.abi.datatypes.generated.Uint256(start), 
                new org.web3j.abi.datatypes.generated.Uint256(end)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getInfoAccessoUtentiRange(String indirizzoUtente, BigInteger start, BigInteger end) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETINFOACCESSOUTENTIRANGE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, indirizzoUtente), 
                new org.web3j.abi.datatypes.generated.Uint256(start), 
                new org.web3j.abi.datatypes.generated.Uint256(end)), 
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
}

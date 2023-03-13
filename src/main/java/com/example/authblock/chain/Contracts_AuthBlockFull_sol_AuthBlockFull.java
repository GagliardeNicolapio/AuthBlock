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
    public static final String BINARY = "608060405234801561001057600080fd5b5061001a3361001f565b61006f565b600080546001600160a01b038381166001600160a01b0319831681178455604051919092169283917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e09190a35050565b611cab8061007e6000396000f3fe6080604052600436106100e85760003560e01c8063761bdc1e1161008a578063bf0e054a11610059578063bf0e054a14610296578063ce0f731a146102cc578063d587eaa3146102ec578063f2fde38b146102f457600080fd5b8063761bdc1e146101ea5780638da5cb5b1461020a5780639a133fae14610232578063b023ff5b1461025257600080fd5b806350f18633116100c657806350f1863314610175578063557c3d5a14610195578063715018a6146101b5578063715fbd8f146101ca57600080fd5b806332ea715f146100ed5780633575a1ff14610123578063373cf20214610145575b600080fd5b3480156100f957600080fd5b5061010d61010836600461158e565b610314565b60405161011a91906115e5565b60405180910390f35b34801561012f57600080fd5b5061014361013e366004611618565b6106b7565b005b34801561015157600080fd5b5061016561016036600461165a565b61082d565b604051901515815260200161011a565b34801561018157600080fd5b506101436101903660046117c9565b61086e565b3480156101a157600080fd5b5061010d6101b036600461158e565b610b44565b3480156101c157600080fd5b50610143610d93565b3480156101d657600080fd5b506101436101e53660046117c9565b610da7565b3480156101f657600080fd5b5061016561020536600461184e565b610e72565b34801561021657600080fd5b506000546040516001600160a01b03909116815260200161011a565b34801561023e57600080fd5b5061010d61024d366004611870565b610eb1565b34801561025e57600080fd5b5061028861026d36600461184e565b6001600160a01b031660009081526003602052604090205490565b60405190815260200161011a565b3480156102a257600080fd5b506102886102b136600461184e565b6001600160a01b031660009081526001602052604090205490565b3480156102d857600080fd5b5061010d6102e7366004611870565b6110db565b6101436111d9565b34801561030057600080fd5b5061014361030f36600461184e565b61125e565b606061031e6112d7565b8183106103725760405162461bcd60e51b815260206004820152601a60248201527f7374617274206e6f6e206527206d696e6f726520646920656e6400000000000060448201526064015b60405180910390fd5b6001600160a01b0384166000908152600160205260409020548211156103d65760405162461bcd60e51b8152602060048201526019602482015278195b99081949c81b5859d9da5bdc9948191a481b195b99da1d603a1b6044820152606401610369565b6001600160a01b038416600090815260016020526040812080546105ed9190869081106104055761040561189a565b90600052602060002090600502016040518060a00160405290816000820154815260200160018201548152602001600282018054610442906118b0565b80601f016020809104026020016040519081016040528092919081815260200182805461046e906118b0565b80156104bb5780601f10610490576101008083540402835291602001916104bb565b820191906000526020600020905b81548152906001019060200180831161049e57829003601f168201915b505050505081526020016003820180546104d4906118b0565b80601f0160208091040260200160405190810160405280929190818152602001828054610500906118b0565b801561054d5780601f106105225761010080835404028352916020019161054d565b820191906000526020600020905b81548152906001019060200180831161053057829003601f168201915b50505050508152602001600482018054610566906118b0565b80601f0160208091040260200160405190810160405280929190818152602001828054610592906118b0565b80156105df5780601f106105b4576101008083540402835291602001916105df565b820191906000526020600020905b8154815290600101906020018083116105c257829003601f168201915b505050505081525050611331565b6040516020016105fd91906118ea565b60408051601f198184030181529190529050600061061c856001611929565b90505b8381101561068c576001600160a01b03861660009081526001602052604090208054839161065791849081106104055761040561189a565b60405160200161066892919061193c565b6040516020818303038152906040529150808061068490611978565b91505061061f565b508060405160200161069e9190611991565b60408051808303601f1901815291905295945050505050565b6106bf6112d7565b6001600160a01b03841660009081526001602052604090205482106106e357600080fd5b6001600160a01b038316600090815260036020526040902054811061070757600080fd5b6001600160a01b03831660009081526003602052604090208054829081106107315761073161189a565b90600052602060002090600302016002015460001461074f57600080fd5b6001600160a01b03841660009081526001602052604090208054839081106107795761077961189a565b90600052602060002090600502016001015460001461079757600080fd5b6001600160a01b0384166000908152600160205260409020805442918291859081106107c5576107c561189a565b9060005260206000209060050201600101819055508060036000866001600160a01b03166001600160a01b0316815260200190815260200160002083815481106108115761081161189a565b9060005260206000209060030201600201819055505050505050565b6001600160a01b03808316600090815260026020908152604080832093851683529290529081208054829190610862906118b0565b90501190505b92915050565b6108766112d7565b6040805160a08101825242808252600060208084018290526001600160a01b03808a168352600282528583209089168352905283812080549294919392918301916108c0906118b0565b80601f01602080910402602001604051908101604052809291908181526020018280546108ec906118b0565b80156109395780601f1061090e57610100808354040283529160200191610939565b820191906000526020600020905b81548152906001019060200180831161091c57829003601f168201915b50505050508152602001856001815181106109565761095661189a565b60200260200101518152602001856002815181106109765761097661189a565b6020026020010151815250905060006040518060600160405280856000815181106109a3576109a361189a565b6020908102919091018101518252818101869052600060409283018190526001600160a01b038b16815260018083528382208054808301825590835291839020875160059093020191825591860151918101919091559084015191925083916002820190610a119082611a05565b5060608201516003820190610a269082611a05565b5060808201516004820190610a3b9082611a05565b5050506001600160a01b03861660009081526003602081815260408320805460018101825590845292208351849390920201908190610a7a9082611a05565b50602082015181600101556040820151816002015550507ff158013eb12ed8b7af55bab269a0776b618bb20341f45c634b93991a09554048878760018060008c6001600160a01b03166001600160a01b0316815260200190815260200160002080549050610ae89190611ac5565b6001600160a01b038a16600090815260036020526040902054610b0d90600190611ac5565b604080516001600160a01b03958616815294909316602085015291830152606082015260800160405180910390a150505050505050565b6060610b4e6112d7565b818310610b9d5760405162461bcd60e51b815260206004820152601a60248201527f7374617274206e6f6e206527206d696e6f726520646920656e640000000000006044820152606401610369565b6001600160a01b038416600090815260036020526040902054821115610c015760405162461bcd60e51b8152602060048201526019602482015278195b99081949c81b5859d9da5bdc9948191a481b195b99da1d603a1b6044820152606401610369565b6001600160a01b03841660009081526003602052604081208054610cf4919086908110610c3057610c3061189a565b9060005260206000209060030201604051806060016040529081600082018054610c59906118b0565b80601f0160208091040260200160405190810160405280929190818152602001828054610c85906118b0565b8015610cd25780601f10610ca757610100808354040283529160200191610cd2565b820191906000526020600020905b815481529060010190602001808311610cb557829003601f168201915b5050505050815260200160018201548152602001600282015481525050611384565b604051602001610d0491906118ea565b60408051601f1981840301815291905290506000610d23856001611929565b90505b8381101561068c576001600160a01b038616600090815260036020526040902080548391610d5e9184908110610c3057610c3061189a565b604051602001610d6f92919061193c565b60405160208183030381529060405291508080610d8b90611978565b915050610d26565b610d9b6112d7565b610da560006113b7565b565b610daf6112d7565b600082600281518110610dc457610dc461189a565b60200260200101515111610e105760405162461bcd60e51b8152602060048201526013602482015272757365726e616d65206e6f6e2076616c69646160681b6044820152606401610369565b81600281518110610e2357610e2361189a565b6020908102919091018101516001600160a01b03808716600090815260028452604080822092881682529190935290912090610e5f9082611a05565b50610e6c8484848461086e565b50505050565b6001600160a01b0381166000908152600160205260408120541515806108685750506001600160a01b0316600090815260036020526040902054151590565b6060610ebb6112d7565b6001600160a01b0383166000908152600160205260408120805484908110610ee557610ee561189a565b90600052602060002090600502016040518060a00160405290816000820154815260200160018201548152602001600282018054610f22906118b0565b80601f0160208091040260200160405190810160405280929190818152602001828054610f4e906118b0565b8015610f9b5780601f10610f7057610100808354040283529160200191610f9b565b820191906000526020600020905b815481529060010190602001808311610f7e57829003601f168201915b50505050508152602001600382018054610fb4906118b0565b80601f0160208091040260200160405190810160405280929190818152602001828054610fe0906118b0565b801561102d5780601f106110025761010080835404028352916020019161102d565b820191906000526020600020905b81548152906001019060200180831161101057829003601f168201915b50505050508152602001600482018054611046906118b0565b80601f0160208091040260200160405190810160405280929190818152602001828054611072906118b0565b80156110bf5780601f10611094576101008083540402835291602001916110bf565b820191906000526020600020905b8154815290600101906020018083116110a257829003601f168201915b50505050508152505090506110d381611331565b949350505050565b60606110e56112d7565b6001600160a01b038316600090815260036020526040812080548490811061110f5761110f61189a565b9060005260206000209060030201604051806060016040529081600082018054611138906118b0565b80601f0160208091040260200160405190810160405280929190818152602001828054611164906118b0565b80156111b15780601f10611186576101008083540402835291602001916111b1565b820191906000526020600020905b81548152906001019060200180831161119457829003601f168201915b505050505081526020016001820154815260200160028201548152505090506110d381611384565b670de0b6b3a764000034116112295760405162461bcd60e51b81526020600482015260166024820152752134b9b7b3b730903830b3b0b93290189032ba3432b960511b6044820152606401610369565b6040513381527fb82feae9da1971f4db0fe644ffdcd97a911db5ce2ca18420226c08831a30e3f09060200160405180910390a1565b6112666112d7565b6001600160a01b0381166112cb5760405162461bcd60e51b815260206004820152602660248201527f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206160448201526564647265737360d01b6064820152608401610369565b6112d4816113b7565b50565b6000546001600160a01b03163314610da55760405162461bcd60e51b815260206004820181905260248201527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e65726044820152606401610369565b60606113408260000151611407565b61134d8360200151611407565b60408085015160608601516080870151925161136e95949390602001611ad8565b6040516020818303038152906040529050919050565b606081600001516113988360200151611407565b6113a58460400151611407565b60405160200161136e93929190611bd9565b600080546001600160a01b038381166001600160a01b0319831681178455604051919092169283917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e09190a35050565b606060006114148361149a565b600101905060008167ffffffffffffffff8111156114345761143461168d565b6040519080825280601f01601f19166020018201604052801561145e576020820181803683370190505b5090508181016020015b600019016f181899199a1a9b1b9c1cb0b131b232b360811b600a86061a8153600a850494508461146857509392505050565b60008072184f03e93ff9f4daa797ed6e38ed64bf6a1f0160401b83106114d95772184f03e93ff9f4daa797ed6e38ed64bf6a1f0160401b830492506040015b6d04ee2d6d415b85acef81000000008310611505576d04ee2d6d415b85acef8100000000830492506020015b662386f26fc10000831061152357662386f26fc10000830492506010015b6305f5e100831061153b576305f5e100830492506008015b612710831061154f57612710830492506004015b60648310611561576064830492506002015b600a83106108685760010192915050565b80356001600160a01b038116811461158957600080fd5b919050565b6000806000606084860312156115a357600080fd5b6115ac84611572565b95602085013595506040909401359392505050565b60005b838110156115dc5781810151838201526020016115c4565b50506000910152565b60208152600082518060208401526116048160408501602087016115c1565b601f01601f19169190910160400192915050565b6000806000806080858703121561162e57600080fd5b61163785611572565b935061164560208601611572565b93969395505050506040820135916060013590565b6000806040838503121561166d57600080fd5b61167683611572565b915061168460208401611572565b90509250929050565b634e487b7160e01b600052604160045260246000fd5b604051601f8201601f1916810167ffffffffffffffff811182821017156116cc576116cc61168d565b604052919050565b6000601f83818401126116e657600080fd5b8235602067ffffffffffffffff808311156117035761170361168d565b8260051b6117128382016116a3565b938452868101830193838101908986111561172c57600080fd5b84890192505b858310156117bc5782358481111561174a5760008081fd5b8901603f81018b1361175c5760008081fd5b858101356040868211156117725761177261168d565b611783828b01601f191689016116a3565b8281528d828486010111156117985760008081fd5b828285018a8301376000928101890192909252508352509184019190840190611732565b9998505050505050505050565b600080600080608085870312156117df57600080fd5b6117e885611572565b93506117f660208601611572565b9250604085013567ffffffffffffffff8082111561181357600080fd5b61181f888389016116d4565b9350606087013591508082111561183557600080fd5b50611842878288016116d4565b91505092959194509250565b60006020828403121561186057600080fd5b61186982611572565b9392505050565b6000806040838503121561188357600080fd5b61188c83611572565b946020939093013593505050565b634e487b7160e01b600052603260045260246000fd5b600181811c908216806118c457607f821691505b6020821081036118e457634e487b7160e01b600052602260045260246000fd5b50919050565b605b60f81b8152600082516119068160018501602087016115c1565b9190910160010192915050565b634e487b7160e01b600052601160045260246000fd5b8082018082111561086857610868611913565b6000835161194e8184602088016115c1565b600b60fa1b908301908152835161196c8160018401602088016115c1565b01600101949350505050565b60006001820161198a5761198a611913565b5060010190565b600082516119a38184602087016115c1565b605d60f81b920191825250600101919050565b601f821115611a0057600081815260208120601f850160051c810160208610156119dd5750805b601f850160051c820191505b818110156119fc578281556001016119e9565b5050505b505050565b815167ffffffffffffffff811115611a1f57611a1f61168d565b611a3381611a2d84546118b0565b846119b6565b602080601f831160018114611a685760008415611a505750858301515b600019600386901b1c1916600185901b1785556119fc565b600085815260208120601f198616915b82811015611a9757888601518255948401946001909101908401611a78565b5085821015611ab55787850151600019600388901b60f8161c191681555b5050505050600190811b01905550565b8181038181111561086857610868611913565b6a3db7b930a637b3b4b71d1160a91b81528551600090611aff81600b850160208b016115c1565b6d11161037b930a637b3b7baba1d1160911b600b918401918201528651611b2d816019840160208b016115c1565b6c1116103ab9b2b93730b6b29d1160991b601992909101918201528551611b5b816026840160208a016115c1565b6d1116103ab9b2b920b3b2b73a1d1160911b602692909101918201528451611b8a8160348401602089016115c1565b6d11161034b820b2323932b9b99d1160911b603492909101918201528351611bb98160428401602088016115c1565b01611bcb6042820161227d60f01b9052565b604401979650505050505050565b693dbab93629b4ba379d1160b11b81528351600090611bff81600a8501602089016115c1565b6b111637b930a637b3b4b71d1160a11b600a918401918201528451611c2b8160168401602089016115c1565b6c111637b930a637b3b7baba1d1160991b601692909101918201528351611c598160238401602088016115c1565b61227d60f01b602392909101918201526025019594505050505056fea264697066735822122055c5ea437f7af9757e107df2351de9aa148351998aca3d222c2ddda795f1e49a64736f6c63430008120033";

    public static final String FUNC_CHECKUSER = "checkUser";

    public static final String FUNC_GETINFOACCESSOSITO = "getInfoAccessoSito";

    public static final String FUNC_GETINFOACCESSOSITORANGE = "getInfoAccessoSitoRange";

    public static final String FUNC_GETINFOACCESSOUTENTE = "getInfoAccessoUtente";

    public static final String FUNC_GETINFOACCESSOUTENTIRANGE = "getInfoAccessoUtentiRange";

    public static final String FUNC_GETNUMBERACCESSISITO = "getNumberAccessiSito";

    public static final String FUNC_GETNUMBERACCESSIUTENTE = "getNumberAccessiUtente";

    public static final String FUNC_INSERTACCESSO = "insertAccesso";

    public static final String FUNC_INSERTLOGOUT = "insertLogout";

    public static final String FUNC_INSERTUSER = "insertUser";

    public static final String FUNC_ISPRESENT = "isPresent";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_PAGAMENTOFULL = "pagamentoFull";

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

   /* public static List<PagamentoRicevutoEventResponse> getPagamentoRicevutoEvents(TransactionReceipt transactionReceipt) {
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

    public RemoteFunctionCall<String> getInfoAccessoSitoRange(String indirizzoSito, BigInteger start, BigInteger end) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETINFOACCESSOSITORANGE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, indirizzoSito), 
                new org.web3j.abi.datatypes.generated.Uint256(start), 
                new org.web3j.abi.datatypes.generated.Uint256(end)), 
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

    public RemoteFunctionCall<TransactionReceipt> pagamentoFull(BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_PAGAMENTOFULL, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
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
